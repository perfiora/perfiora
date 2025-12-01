package io.perfiora.virtual;

/**
 * Internal representation for column.
 */
public class VirtualColumn extends VirtualObject {

    private VirtualDataType dataType;

    private VirtualIndex index;

    private boolean isNullable;

    private Integer length;

    public VirtualDataType getDataType() {
        return dataType;
    }

    public void setDataType(VirtualDataType dataType) {
        this.dataType = dataType;
    }

    public VirtualIndex getIndex() {
        return index;
    }

    public void setIndex(VirtualIndex index) {
        this.index = index;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
