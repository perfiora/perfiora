package io.perfiora.virtual;

/**
 * Internal representation for column.
 */
public class VirtualColumn extends VirtualObject {

    private VirtualDataType dataType;

    private VirtualIndex index;

    private boolean isNullable;

    private Integer length;
}
