package org.eclipse.dawnsci.xdi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class XDIFile {
	public int nmetadata;         /* number of metadata family/key/val metadata */
	public int narrays;           /* number of arrays */
	public int npts;              /* number of data points for all arrays */
	public int narray_labels;     /* number of labeled arrays (may be < narrays) */
	public int nouter = 1;            /* number of points in outer scan */
	public int error_lineno = -1;      /* line numberfor any existing error */
	public double dspacing = -1.0;        /* monochromator d spacing */
	public String xdi_libversion = XDI.XDI_VERSION;  /* XDI version of library */
	public String xdi_version = "";     /* XDI version string from file*/
	public String extra_version = "";   /* Extra version strings from first line of file */
	public String filename;        /* name of file */
	public String element = "";    /* atomic symbol for element */
	public String edge = "";       /* name of absorption edge: "K", "L1", ... */
	public String comments = "";   /* multi-line, user-supplied comment */
	/*public String error_line = "";*/      /* text of line with any existing error */
	/*public String error_message = "";*/
	public String[] array_labels;  /* labels for arrays */
	public String outer_label = "";     /* labels for outer array */
	public String[] array_units;   /* units for arrays */
	public String[] meta_families; /* family for metadata from file header */
	public String[] meta_keywords; /* keyword for metadata from file header */
	public String[] meta_values;   /* value for metadata from file header */
	public double[][] array;       /* 2D array of all array data */
	public double[] outer_array;   /* array of outer breakpoints for multi-dimensional data */
	public int[] outer_breakpts;  /* array of breakpoints for outer array */
	
	public static final String TOK_VERSION    =  "XDI/";            /* version marker in file -- required on line 1 */
	public static final String TOK_COMM       =  "#";               /* comment character, at start of line */
	public static final String TOK_DELIM      =  ":";               /* delimiter between metadata name and value */
	public static final String TOK_DOT        =  "\\.";               /* delimiter between metadata family and key */
	public static final String TOK_EDGE       =  "element.edge";    /* absorbption edge name */
	public static final String TOK_ELEM       =  "element.symbol";  /* atomic symbol of absorbing element */
	public static final String TOK_COLUMN     =  "column.";         /* column label (followed by integer <= 64) */
	public static final String TOK_DSPACE     =  "mono.d_spacing";  /* mono d_spacing, in Angstroms */
	public static final String TOK_TIMESTAMP  =  "scan.start_time"; /* scan time */
	public static final String TOK_TIMESTART  =  "scan.start_time"; /* scan time */
	public static final String TOK_TIMEEND    =  "scan.end_time";   /* scan time */
	public static final String TOK_USERCOM_0  =  "///";             /* start multi-line user comment */
	public static final String TOK_USERCOM_1  =  "---";             /* end multi-line user comment */
	public static final String TOK_COL_ENERGY = "energy";          /* name of energy column */
	public static final String TOK_COL_ANGLE  = "angle";           /* name of angle column */
	public static final String TOK_OUTER_VAL  = "outer.value";     /* value for outer scan position */
	public static final String TOK_OUTER_NAME = "outer.name"; /* name for outer scan position */
	
	public static final String[] ValidEdges =
		  {"K", "L", "L1", "L2", "L3",
		   "M", "M1", "M2", "M3", "M4", "M5",
		   "N", "N1", "N2", "N3", "N4", "N5", "N6", "N7",
		   "O", "O1", "O2", "O3", "O4", "O5", "O6", "O7"};

		/* "P", "P1", "P2", "P3", "P4", "P5", "P6", "P7" */

	public static final String[] ValidElems =
		  {"H",  "He", "Li", "Be", "B",  "C",  "N",  "O",
		   "F",  "Ne", "Na", "Mg", "Al", "Si", "P",  "S",
		   "Cl", "Ar", "K",  "Ca", "Sc", "Ti", "V",  "Cr",
		   "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge",
		   "As", "Se", "Br", "Kr", "Rb", "Sr", "Y",  "Zr",
		   "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd",
		   "In", "Sn", "Sb", "Te", "I",  "Xe", "Cs", "Ba",
		   "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd",
		   "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf",
		   "Ta", "W",  "Re", "Os", "Ir", "Pt", "Au", "Hg",
		   "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra",
		   "Ac", "Th", "Pa", "U",  "Np", "Pu", "Am", "Cm",
		   "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf",
		   "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn",
		   "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo"};

	public static final String XDI_VERSION = "1.1.0";   /* XDI version marker */

	public static final int MAX_COLUMNS = 128; /* maximum number of supported data columns */
	
	/* List of required metadata items */
	public static final String[] RequiredMetadata =
	  {                             /* these are the bits of the errorcode returned by XDI_recommended_metadata */
	    "Element.symbol",		/* 2^0 */
	    "Element.edge",     	/* 2^1 */
	    "Mono.d_spacing",		/* 2^2 */
	  };

	/* List of recommended metadata items */
	public static final String[] RecommendedMetadata =
	  {                             /* these are the bits of the errorcode returned by XDI_recommended_metadata */
	    "Facility.name",		/* 2^0 */
	    "Facility.xray_source",	/* 2^1 */
	    "Beamline.name",		/* 2^2 */
	    "Scan.start_time",		/* 2^3 */
	    "Column.1",			/* 2^4 */
	};

	private static final String FAMILYNAME = "^[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_][ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_0123456789]+$";
	private static final String KEYNAME = "^[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_0123456789]+$";
	private static final String DATALINE = "^([ \\t]*[-+]*?[0-9\\.])";
	
	public XDIFile(String filename) throws XDIException {
		boolean has_minusline = false;
		int ignored_headerline = -1;
		int nheader = 0;
		int ndict   = -1;
		int maxcol = 0;
		String[] col_labels = new String[MAX_COLUMNS];
		String[] col_units = new String[MAX_COLUMNS];
		
		for (int i = 0 ; i < MAX_COLUMNS ; i++) {
			col_labels[i] = String.format("col%d", i);
			col_units[i] = "";
		}

		List<String> textlines;
		try {
			 textlines = Files.readAllLines(new File(filename).toPath());
		} catch (IOException e) {
			throw new XDIException(XDIException.ERR_NOTXDI);
		}
		
		/* check first line for XDI header, get version info */
		if (textlines.get(0).startsWith(TOK_COMM))  {
			String firstline = textlines.get(0).trim();
			firstline = firstline.substring(1).trim();
		    String[] cwords = firstline.split("\\s+");
		    int nwords = cwords.length;
		    if (nwords < 1) {
		    	throw new XDIException(XDIException.ERR_NOTXDI);
		    }
		    if (!cwords[0].toLowerCase().startsWith(TOK_VERSION.toLowerCase())) {
		    	throw new XDIException(XDIException.ERR_NOTXDI);
		    }
		    xdi_version = cwords[0].replace(TOK_VERSION, "");
		    if (nwords > 1) { /* extra version tags */
		      extra_version = cwords[1];
		    }
		}
	
		int i;
		final int ilen = textlines.size();
	
		/* find number of header lines,
	     * nheader= index of first line that does not start with '#'
		 */
		for (i = 1; i < ilen ; i++) {
			String[] splitted = textlines.get(i).trim().split(DATALINE);
			if (textlines.get(i).length() > 3 && splitted.length > 0 && !splitted[0].equals(textlines.get(i).trim()))
		      break;
		}
		nheader = i+1;
		if (nheader < 1) {
			nheader = 1;
		}
		
		meta_families = new String[(int) nheader];
		meta_keywords = new String[(int) nheader];
		meta_values = new String[(int) nheader];
		
		int mode = 0; /*  metadata (Family.Member: Value) mode */
		String line, fullline;
		double outer_arr0 = 0.0;
		boolean has_angle, has_energy;
		
		for (i = 1; i < nheader; i++) {

		    if (textlines.get(i).startsWith(TOK_COMM))  {
		    	line = textlines.get(i);
		    	fullline = textlines.get(i);
		    	line = line.substring(1).trim();
		    	if (line.equals(""))
		    		continue;
		    	fullline = fullline.substring(1);
		    	String[] words = line.split(TOK_DELIM, 2);
		    	int nwords = words.length;
		    	if (nwords < 1) {
		    		continue;
		    	}
		    	String mkey = words[0];

		    	if ((mode==0) && (nwords == 2)) { /* metadata */
		    		String mval = words[1].trim();
		    		if (mval.isEmpty())
	    				throw new XDIException(XDIException.ERR_META_FAMNAME);
		    		words = words[0].trim().split(TOK_DOT, 2);
		    		nwords = words.length;
		    		if (nwords > 1) {
		    			ndict++;
		    			meta_values[ndict] = mval;
		    			/* need to test words[0] and words[1] as valid family/key names
			    		   family name cannot start with number
			      		   key cannot contain '.'
		    		     */
		    			words[0] = words[0].trim();
		    			if (!words[0].matches(FAMILYNAME))
		    				throw new XDIException(XDIException.ERR_META_FAMNAME);

		    			words[1] = words[1].trim();
		    			if (!words[1].matches(KEYNAME))
		    				throw new XDIException(XDIException.ERR_META_KEYNAME);
		    		  
		    			meta_families[ndict] = words[0];
		    			meta_keywords[ndict] = words[1];
		    		} else {
		    			throw new XDIException(XDIException.ERR_META_FORMAT);
		    		}
		    		if (mkey.toLowerCase().startsWith(TOK_COLUMN)) {
		    			int j;
		    			try {
		    				j = Integer.parseInt(mkey.substring(TOK_COLUMN.length())) - 1;
		    			} catch (NumberFormatException e) {
		    				j = -1;
		    			}
		    			if ((j > -1) && (j < MAX_COLUMNS)) {
		    				String[] cwords = mval.trim().split("\\s+");
		    				int ncols = cwords.length;
		    				col_labels[j] = cwords[0];
		    				if (ncols == 2) {
		    					col_units[j] = cwords[1];
		    				}
		    				maxcol =  Math.max(maxcol, j);
		    			}
		    			/* ELEMENT EDGE (accept any two characters, validate later) */
		    		} else if (mkey.equalsIgnoreCase(TOK_EDGE)) {
		    			edge = mval.substring(0, Math.min(2, mval.length()));
		    			/* ELEMENT NAME (accept any three characters, validate later) */
		    		} else if (mkey.equalsIgnoreCase(TOK_ELEM)) {
		    			element = mval.substring(0, Math.min(3, mval.length()));
		    			/* MONO D-SPACING */
		    		} else if (mkey.equalsIgnoreCase(TOK_DSPACE)) {
		    			try {
		    				dspacing = Double.parseDouble(mval);
		    			} catch (NumberFormatException e) {
		    			}
		    			/* OUTER ARRAY NAME */
		    		} else if (mkey.equalsIgnoreCase(TOK_OUTER_NAME)) {
		    			outer_label = mval;
		    			/* OUTER ARRAY VALUE */
		    		} else if (mkey.equalsIgnoreCase(TOK_OUTER_VAL)) {
		    			try {
		    				outer_arr0 = Double.parseDouble(mval);
		    			} catch (NumberFormatException e) {
		    				throw new XDIException(XDIException.ERR_NONNUMERIC);
		    			}
		    		}

		    	} else if (mkey.toLowerCase().startsWith(TOK_USERCOM_0)) {
		    		mode = 1;
		    	} else if (mkey.toLowerCase().startsWith(TOK_USERCOM_1)) {
		    		mode = 2;
		    		has_minusline = true;
		    	} else if (mode==1) {
		    		comments += fullline + "\n";
		    	} else if (mode == 0) {
		    		throw new XDIException(XDIException.ERR_META_FORMAT);
		    	}
		    } else {
		    	if ((ignored_headerline < 0) && (has_minusline == false)) {
		    		ignored_headerline = i;
		    	}
		    }
		}
		if (ignored_headerline > 0) {
		    //strcpy(xdifile->error_message, "contains unrecognized header lines");
		    //iret = WRN_IGNOREDMETA;
			System.err.println("Warning: unrecognized header lines detected");
		}
		if (has_minusline == false) {
		    //strcpy(xdifile->error_message, "no line of minus signs '#-----' separating header from data");
		    //iret = WRN_NOMINUSLINE;
		    System.err.println("no line of minus signs '#-----' separating header from data");
		}

		int npts_ = ilen - nheader + 1;

		nouter = npts_ - 1;
		if (nouter < 1) {
			nouter = 1;
		}
		double[] outer_arr = new double[nouter];
		int[] outer_pts = new int[nouter];
		outer_arr[0] = outer_arr0;
		outer_pts[0] = 1;

		line = textlines.get(i).trim();
		String[] words = line.split("\\s+");
		int ncols = words.length;

		this.filename = filename;

		maxcol++;
		if (ncols < 1) {
			ncols = 1;
		}
		array_labels = new String[ncols];
		array_units  = new String[ncols];
		has_energy = false;
		has_angle  = false;
		for (int j = 0; j < ncols; j++) {
			array_labels[j] = col_labels[j];
		    array_units[j] = col_units[j];
		    if (col_labels[j].equalsIgnoreCase(TOK_COL_ENERGY)) {
		      has_energy = true;
		    } else if (col_labels[j].equalsIgnoreCase(TOK_COL_ANGLE)) {
		      has_angle = true;
		    }
		}


		/* check for mono d-spacing if angle is given but not energy*/
		if ((has_angle)  && (!has_energy) && (dspacing < 0)) {
		    System.err.println("no mono.d_spacing given with angle array");
		}

		  /* set size of data arrays */
		array = new double[ncols][];
		if (npts_ < 0) {
			npts_ = 0;
		}
		for (int j = 0; j < ncols; j++) {
		    array[j] = new double[npts_];
		    try {
		    	array[j][0] = Double.parseDouble(words[j].trim());
		    } catch (NumberFormatException e) {
		    	throw new XDIException(XDIException.ERR_NONNUMERIC);
		    }
		}

		/* loop through data table, inserting data into xdifile->array */
		int ipt = 0;
		int iouter = 1;
		for (i = nheader-2; i < ilen; i++) {
			/* may find a header line interspersed in array data */
			String interline = textlines.get(i).trim();
		    error_lineno = i;
		    //error_line = interline;

		    if (interline.startsWith(TOK_COMM))  {
		    	interline = interline.substring(1).trim();
		    	words = interline.split(TOK_DELIM);
		    	int nwords = words.length;
		    	if (nwords < 2) {
		    		continue;
		    	}
		    	String mkey = words[0];
		    	if (mkey.equalsIgnoreCase(TOK_OUTER_VAL)) {
		    		double dval;
		    		try {
		    			dval = Double.parseDouble(words[1]);
		    		} catch (NumberFormatException e) {
		    			throw new XDIException(XDIException.ERR_NONNUMERIC);
		    		}
		    		outer_arr[iouter] = dval;
		    		outer_pts[iouter] = ipt;
		    		++iouter;
		    	}
		    } else {
		    	/* COPY_STRING(line, textlines[i]); */
		    	words = interline.split("\\s+");
		    	int icol = words.length;
		    	if (icol != ncols) {
		    		throw new XDIException(XDIException.ERR_NCOLS_CHANGE);
		    	}
		    	icol = Math.min(ncols, icol);
		    	for (int j = 0; j < icol; j++) {
		    		double dval;
		    		try {
		    			dval = Double.parseDouble(words[j]);
		    		} catch (NumberFormatException e) {
		    			throw new XDIException(XDIException.ERR_NONNUMERIC);
		    		}
		    		array[j][ipt] = dval;
		    	}
		      ++ipt;
		    }
		}

		/* success */
		error_lineno = 0;
		//error_line = "";

		npts = ipt;
		nouter = iouter;
		narrays = ncols;
		narray_labels = Math.min(ncols, maxcol);
		nmetadata = ndict+1;
		meta_families = Arrays.copyOf(meta_families, nmetadata);
		meta_keywords = Arrays.copyOf(meta_keywords, nmetadata);
		meta_values = Arrays.copyOf(meta_values, nmetadata);
		if (iouter < 1) {
			iouter = 1;
		}
		outer_array = new double[iouter];
		outer_breakpts = new int[iouter];
		for (int j= 0; j < iouter; j++) {
		    outer_array[j] = outer_arr[j];
		    outer_breakpts[j] = outer_pts[j];
		}
	}
}
