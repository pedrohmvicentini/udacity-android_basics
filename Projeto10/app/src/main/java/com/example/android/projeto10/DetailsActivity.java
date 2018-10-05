package com.example.android.projeto10;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.projeto10.data.InventoryContract;
import com.example.android.projeto10.data.InventoryDbHelper;
import com.example.android.projeto10.data.InventoryItem;

/**
 * Created by Pedro on 09/02/2018.
 */

public class DetailsActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private InventoryDbHelper mDbHelper;
    private EditText mTitleEdit;
    private EditText mPriceEdit;
    private EditText mQuantityEdit;
    private EditText mSupplierNameEdit;
    private EditText mSupplierPhoneEdit;
    private EditText mSupplierEmailEdit;
    private long mCurrentItemId;
    private ImageButton mDecreaseQuantity;
    private ImageButton mIncreaseQuantity;
    private Button mImageBtn;
    private ImageView mImageView;
    private Uri mActualUri;
    private static final int PICK_IMAGE_REQUEST = 0;
    private Boolean mInfoItemHasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTitleEdit = (EditText) findViewById(R.id.title_edit);
        mPriceEdit = (EditText) findViewById(R.id.price_edit);
        mQuantityEdit = (EditText) findViewById(R.id.quantity_edit);
        mSupplierNameEdit = (EditText) findViewById(R.id.supplier_name_edit);
        mSupplierPhoneEdit = (EditText) findViewById(R.id.supplier_phone_edit);
        mSupplierEmailEdit = (EditText) findViewById(R.id.supplier_email_edit);
        mDecreaseQuantity = (ImageButton) findViewById(R.id.decrease_quantity);
        mIncreaseQuantity = (ImageButton) findViewById(R.id.increase_quantity);
        mImageBtn = (Button) findViewById(R.id.select_image);
        mImageView = (ImageView) findViewById(R.id.image_view);

        mDbHelper = new InventoryDbHelper(this);
        mCurrentItemId = getIntent().getLongExtra("itemId", 0);

        if (mCurrentItemId == 0) {
            setTitle(getString(R.string.editor_activity_title_new_item));
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_item));
            addValuesToEditItem(mCurrentItemId);
        }

        mDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtractOneToQuantity();
                mInfoItemHasChanged = true;
            }
        });

        mIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumOneToQuantity();
                mInfoItemHasChanged = true;
            }
        });

        mImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToOpenImageSelector();
                mInfoItemHasChanged = true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mInfoItemHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void subtractOneToQuantity() {
        String previousValueString = mQuantityEdit.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            return;
        } else if (previousValueString.equals("0")) {
            return;
        } else {
            previousValue = Integer.parseInt(previousValueString);
            mQuantityEdit.setText(String.valueOf(previousValue - 1));
        }
    }

    private void sumOneToQuantity() {
        String previousValueString = mQuantityEdit.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueString);
        }
        mQuantityEdit.setText(String.valueOf(previousValue + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentItemId == 0) {
            MenuItem deleteOneItemMenuItem = menu.findItem(R.id.action_delete_item);
            MenuItem deleteAllMenuItem = menu.findItem(R.id.action_delete_all_data);
            MenuItem orderMenuItem = menu.findItem(R.id.action_order);
            deleteOneItemMenuItem.setVisible(false);
            deleteAllMenuItem.setVisible(false);
            orderMenuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                // save item in DB
                if (!addItemToDb()) {
                    // saying to onOptionsItemSelected that user clicked button
                    return true;
                }
                finish();
                return true;
            case android.R.id.home:
                if (!mInfoItemHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(DetailsActivity.this);
                            }
                        };
                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
            case R.id.action_order:
                // dialog with phone and email
                showOrderConfirmationDialog();
                return true;
            case R.id.action_delete_item:
                // delete one item
                showDeleteConfirmationDialog(mCurrentItemId);
                return true;
            case R.id.action_delete_all_data:
                //delete all data
                showDeleteConfirmationDialog(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean addItemToDb() {
        boolean isAllOk = true;
        if (!checkIfValueSet(mTitleEdit, "title")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(mPriceEdit, "price")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(mQuantityEdit, "quantity")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(mSupplierNameEdit, "supplier name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(mSupplierPhoneEdit, "supplier phone")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(mSupplierEmailEdit, "supplier email")) {
            isAllOk = false;
        }
        if (mActualUri == null && mCurrentItemId == 0) {
            isAllOk = false;
            mImageBtn.setError("Missing image");
        }
        if (!isAllOk) {
            return false;
        }

        if (mCurrentItemId == 0) {
            InventoryItem item = new InventoryItem(
                    mTitleEdit.getText().toString().trim(),
                    mPriceEdit.getText().toString().trim(),
                    Integer.parseInt(mQuantityEdit.getText().toString().trim()),
                    mSupplierNameEdit.getText().toString().trim(),
                    mSupplierPhoneEdit.getText().toString().trim(),
                    mSupplierEmailEdit.getText().toString().trim(),
                    mActualUri.toString());
            mDbHelper.insertItem(item);
        } else {
            int quantity = Integer.parseInt(mQuantityEdit.getText().toString().trim());
            mDbHelper.updateItem(mCurrentItemId, quantity);
        }
        return true;
    }

    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    private void addValuesToEditItem(long itemId) {
        Cursor cursor = mDbHelper.readItem(itemId);
        cursor.moveToFirst();
        mTitleEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_TITLE)));
        mPriceEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE)));
        mQuantityEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY)));
        mSupplierNameEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME)));
        mSupplierPhoneEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE)));
        mSupplierEmailEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_EMAIL)));
        mImageView.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_IMAGE))));
        mTitleEdit.setEnabled(false);
        mPriceEdit.setEnabled(false);
        mSupplierNameEdit.setEnabled(false);
        mSupplierPhoneEdit.setEnabled(false);
        mSupplierEmailEdit.setEnabled(false);
        mImageBtn.setEnabled(false);
    }

    private void showOrderConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.order_message);
        builder.setPositiveButton("Telefone", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // intent to phone
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mSupplierPhoneEdit.getText().toString().trim()));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("E-mail", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // intent to email
                Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + mSupplierEmailEdit.getText().toString().trim()));
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Recurrent new order");
                String bodyMessage = "Please send us as soon as possible more " +
                        mTitleEdit.getText().toString().trim() +
                        "!!!";
                intent.putExtra(android.content.Intent.EXTRA_TEXT, bodyMessage);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int deleteAllRowsFromTable() {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.delete(InventoryContract.InventoryEntry.TABLE_NAME, null, null);
    }

    private int deleteOneItemFromTable(long itemId) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        String selection = InventoryContract.InventoryEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(itemId)};
        int rowsDeleted = database.delete(
                InventoryContract.InventoryEntry.TABLE_NAME, selection, selectionArgs);
        return rowsDeleted;
    }

    private void showDeleteConfirmationDialog(final long itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (itemId == 0) {
                    deleteAllRowsFromTable();
                } else {
                    deleteOneItemFromTable(itemId);
                }
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void tryToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
        openImageSelector();
    }

    private void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                    // permission was granted
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mActualUri = resultData.getData();
                mImageView.setImageURI(mActualUri);
                mImageView.invalidate();
            }
        }
    }
}

