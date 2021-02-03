package model;

import javax.persistence.Entity;
import EnumExtension.*;

@Entity
public enum TaskStatus{
   private EnumExtension TODO;

private void setTODO(EnumExtension value) {
    this.TODO = value;
}
private EnumExtension getTODO() {
    return this.TODO;
}
private EnumExtension IN_PROGRESS;

private void setIN_PROGRESS(EnumExtension value) {
    this.IN_PROGRESS = value;
}
private EnumExtension getIN_PROGRESS() {
    return this.IN_PROGRESS;
}
private EnumExtension DONE;

private void setDONE(EnumExtension value) {
    this.DONE = value;
}
private EnumExtension getDONE() {
    return this.DONE;
}
private EnumExtension CLOSED;

private void setCLOSED(EnumExtension value) {
    this.CLOSED = value;
}
private EnumExtension getCLOSED() {
    return this.CLOSED;
}
}
