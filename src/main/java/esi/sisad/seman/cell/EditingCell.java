package esi.sisad.seman.cell;

import esi.sisad.seman.model.Country;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingCell extends TableCell<Country, String> {
    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextFieldNode();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(item);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getValue());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getValue());
                setGraphic(null);
            }
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(null);
    }

    private void createTextFieldNode() {
        textField = new TextField(getValue());
        textField.setStyle(" -fx-background-color: -fx-text-box-border, white;\n" +
                           " -fx-background-insets: 0, 1 1 1 1 ;\n" +
                           " -fx-border-color: none;" +
                           " -fx-background-radius: 1 ;\n" +
                           " -fx-font-size: 14px;");
        textField.setOnAction((e) -> commitEdit(textField.getText()));
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            textField.setStyle("-fx-background-color: -fx-text-box-border, white;");
            if (newValue == null) {
                System.out.println("On editing....");
                commitEdit(textField.getText());
            }
        });
    }

    private String getValue() {
        return getItem() == null ? "" : getItem();
    }
}
