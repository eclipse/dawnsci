package org.eclipse.dawnsci.xdi;

public class XDIException extends Exception {

	private static final long serialVersionUID = -8759726414376627075L;

	/* errors in XDI_required_metadata */
	public static final int REQ_ELEM =             1;
	public static final int REQ_EDGE =             2;
	public static final int REQ_NO_DSPACING =      4;
	public static final int REQ_INVALID_DSPACING = 8;

	/* warnings from reading the XDI file */
	public static final int WRN_NODSPACE =         1;
	public static final int WRN_NOMINUSLINE =      2;
	public static final int WRN_IGNOREDMETA =      4;
	/* warnings from metadata value validation, these are not use bitwise */
	public static final int WRN_NOELEM =         100;
	public static final int WRN_NOEDGE =         101;
	public static final int WRN_REFELEM =        102;
	public static final int WRN_REFEDGE =        103;
	public static final int WRN_NOEXTRA =        104;
	public static final int WRN_BAD_COL1 =       105;
	public static final int WRN_DATE_FORMAT =    106;
	public static final int WRN_DATE_RANGE =     107;
	public static final int WRN_BAD_DSPACING =   108;
	public static final int WRN_BAD_SAMPLE =     109;
	public static final int WRN_BAD_FACILITY =   110;

	/* errors reading the XDI file */
	public static final int ERR_NOTXDI =          -1;	/* used */
	public static final int ERR_META_FAMNAME =    -2;	/* used */
	public static final int ERR_META_KEYNAME =    -4;	/* used */
	public static final int ERR_META_FORMAT =     -8;	/* used */
	public static final int ERR_NCOLS_CHANGE =   -16;	/* used */
	public static final int ERR_NONNUMERIC =     -32;	/* used */
	public static final int ERR_MEMERROR =       -64;	/* NOT used */

	private final int code;
	
	XDIException(int code) {
		super(errorstring(code));
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	private static String errorstring(int errcode) {
		if (errcode == 0) {
			return "";
		}
		if (errcode == ERR_NOTXDI) {
		    return "not an XDI file";
		} else if (errcode == WRN_NOELEM) {
		    return "element.symbol not given or not valid";
		} else if (errcode == WRN_NOEDGE) {
		    return "element.edge not given or not valid";
		} else if (errcode == WRN_REFELEM) {
		    return "element.reference not valid";
		} else if (errcode == WRN_REFEDGE) {
		    return "element.ref_edge not valid";
		} else if (errcode == WRN_NOEXTRA) {
		    return "extension fields used without versioning information";
		} else if (errcode == WRN_NODSPACE) {
		    return "no mono.d_spacing given with angle array";
		} else if (errcode == ERR_META_FAMNAME) {
		    return "invalid family name in meta-data";
		} else if (errcode == ERR_META_KEYNAME) {
		    return "invalid keyword name in meta-data";
		} else if (errcode == ERR_META_FORMAT) {
		    return "metadata not formatted as Family.Key: Value";
		} else if (errcode == WRN_DATE_FORMAT) {
		    return "invalid timestamp: format should be YYYY-MM-DD HH:MM:SS";
		} else if (errcode == WRN_DATE_RANGE) {
		    return "invalid timestamp: date out of valid range";
		} else if (errcode == WRN_NOMINUSLINE) {
		    return "no line of minus signs '#-----' separating header from data";
		} else if (errcode == ERR_NCOLS_CHANGE) {
		    return "number of columns changes in file";
		} else if (errcode == WRN_BAD_DSPACING) {
		    return "non-numeric value for d-spacing";
		} else if (errcode == ERR_NONNUMERIC) {
		    return "non-numeric value in data table";
		} else if (errcode == WRN_IGNOREDMETA) {
		    return "contains unrecognized header lines";
		}
		return "";
	}
}
