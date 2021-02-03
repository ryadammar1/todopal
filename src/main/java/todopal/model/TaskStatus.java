package model;

import javax.persistence.Entity;
import EnumExtension.*;

@Entity
public enum TaskStatus{
	TODO,
	IN_PROGRESS,
	DONE,
	COMPLETED

}
