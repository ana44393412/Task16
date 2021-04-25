package models.pet;

import java.util.Objects;

public class Info {
	private int code;
	private String type;
	private String message;

	public final int getCode() {
		return code;
	}

	public final String getType(){
	 return type;
	}

	public final String getMessage(){
		return message;
	}

	public final void setCode(int code) {
		this.code = code;
	}

	public final void setType(final String type) {
		this.type = type;
	}

	public final void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Info info = (Info) o;
		return code == info.code && Objects.equals(type, info.type) && Objects.equals(message, info.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, type, message);
	}
}
