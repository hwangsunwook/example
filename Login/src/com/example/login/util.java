package com.example.login;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.login.pcom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

public class util {

	static pcom pcom_ptr = new pcom();
	
	public util() {

	}

	public String getAuhtID(Activity act) {
		String tmp = "";
		HashMap<String, String> hm = pcom_ptr.authHM; 
		tmp = null2empty(hm.get("id[0]"));
		return tmp;
	}
	
	public boolean getLoginState(Activity act) {
		boolean tmp = false;
		if (getAuhtID(act).length()>0)  tmp=true;
		return tmp;
	}
	
	public int getAuhtLevel(Activity act) {
		int tmp = -1;
		HashMap<String, String> hm = pcom_ptr.authHM;
		tmp = str2int(hm.get("level[0]"),-1);
		return tmp;
	}
	
	public HashMap<String, String> getAuhtHM(Activity act) {
		HashMap<String, String> hm = pcom_ptr.authHM;
		return hm;
	}
	
	public void setAuhtHM(Activity act, HashMap<String, String> hm) {
		pcom_ptr.authHM = hm;
		
	}
	
	public InputFilter filterAlphaNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, 
				int end, Spanned dest, int dstart, int dend) {
			Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
			if (!ps.matcher(source).matches()) {
				return "";
			}
			return null;
		}
	};
	
	public InputFilter filterJavaLetterOrDigit = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, 
				int end, Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isJavaLetterOrDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};
	
	public InputFilter filterLetterNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, 
				int end, Spanned dest, int dstart, int dend) {
			
//			Pattern ps=Pattern.compile("[a-zA-Z0-9가-R]*");
//			if (!ps.matcher(source).matches()) {
//				return "";
//			}
			for (int i = start; i < end; i++) {
				if (!Character.isLetterOrDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};
	
	public InputFilter filterLetter = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, 
				int end, Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isLetter(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};
	
	public InputFilter filterNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, 
				int end, Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};

	public String getMyPhoneNumber(Activity act) {
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager) act
				.getSystemService(Context.TELEPHONY_SERVICE);
		String tmp = mTelephonyMgr.getLine1Number();
		if (tmp == null)
			tmp = "";
		return tmp;
	}

	public String getEditTextVal(Activity act, int RItem) {
		String tmp = ((EditText) act.findViewById(RItem)).getText().toString();
		if (tmp == null)
			tmp = "";
		return tmp;
	}

	public EditText getEditText(Activity act, int RItem) {
		return ((EditText) act.findViewById(RItem));
	}

	public void goActivity(Context mContext, String menuClass) {
		String findClass = "";
		Class<?> cls;
		Activity actCategory;
		try {
			cls = Class.forName(menuClass);
			actCategory = (Activity) cls.newInstance();
			Intent intent = new Intent(mContext, actCategory.getClass());
			mContext.startActivity(intent);
			findClass = cls.getName();
		} catch (ClassNotFoundException e) {
			findClass = "";
			AlertDialog dialog = new AlertDialog.Builder(mContext).create();
			dialog.setTitle("안내");
			dialog.setMessage("죄송합니다.\n페이지를 찾을 수 없습니다.");
			dialog.setButton("확인", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			dialog.show();
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
	}

	public Activity findAct(Context mContext, String menuClass) {
		String foundClass = "";
		Class<?> cls;
		Activity actCategory = null;
		try {
			cls = Class.forName(menuClass);
			actCategory = (Activity) cls.newInstance();
			foundClass = cls.getName();
		} catch (ClassNotFoundException e) {
			foundClass = "";
			AlertDialog dialog = new AlertDialog.Builder(mContext).create();
			dialog.setTitle("안내");
			dialog.setMessage("죄송합니다.\n페이지를 찾을 수 없습니다.");
			dialog.setButton("확인", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			dialog.show();
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		return actCategory;
	}

	public HashMap<String, String> xml2HashMap(String tmpData, String encoding) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("count", "0");
		try {
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(tmpData
					.getBytes(encoding));
			Document doc = docB.parse(is);
			Element lists = doc.getDocumentElement();
			NodeList dataList = lists.getElementsByTagName("data");
			int c = 0;
			for (int i = 0; i < dataList.getLength(); i++) {
				NodeList dataNodeList = dataList.item(i).getChildNodes();
				for (int j = 0; j < dataNodeList.getLength(); j++) {
					;
					Node itemNode = dataNodeList.item(j);
					if (itemNode.getFirstChild() != null) {
						String nodeName = itemNode.getNodeName();
						String nodeValue = itemNode.getFirstChild()
								.getNodeValue();
						hm.put(nodeName + "[" + i + "]", nodeValue);
					}
				}// for j
				c++;
			}// for i
			hm.put("count", Integer.toString(c));
		} catch (Exception e) {
			Log.e("com.cms.app.util.xml2HashMap", e.getMessage());
		}
		return hm;
	}

	public int str2int(String txt, int mydefault) {
		int num = 0;
		if (txt == null || "".equals(txt)) {
			num = mydefault;
		} else {
			num = Integer.parseInt(txt);
		}
		return num;
	}

	public int str2int(String txt) {
		int num = 0;
		if (txt == null || "".equals(txt)) {

		} else {
			num = double2int(txt);
		}
		return num;
	}

	public int double2int(double val) {
		int tmp = 0;
		Double d = new Double(val);
		tmp = d.intValue();
		return tmp;
	}

	public int double2int(String val) {
		int tmp = 0;
		Double d = new Double(val);
		tmp = d.intValue();
		return tmp;
	}

	public double str2double(String txt) {
		double num = 0;
		if (txt == null || "".equals(txt)) {
			num = 0.0;
		} else {
			num = Double.valueOf(txt).doubleValue();
		}
		return num;
	}

	public long str2long(String txt) {
		long num = 0;
		if (txt == null || "".equals(txt)) {
			num = 0;
		} else {
			num = Long.valueOf(txt).longValue();
		}
		return num;
	}

	public String str_replace(String src, String des, String org) {
		int fromindex = 0;
		int toindex = 0;
		String replaced = "";
		int i = 0;
		if ("".equals(src) || src == null) {
			replaced = org;
		} else {
			while (fromindex >= 0) {
				if (i == 0) {
					toindex = org.indexOf(src, 0);
					if (toindex < 0) {
						replaced = org.substring(0, org.length());
						break;
					} else {
						replaced = org.substring(0, toindex);
						replaced += des;
					}
				} else {
					toindex = org.indexOf(src, fromindex + src.length());
					if (toindex < 0) {
						replaced += org.substring(fromindex + src.length(), org
								.length());
						break;
					} else {
						replaced += org.substring(fromindex + src.length(),
								toindex);
						replaced += des;
					}
				}
				fromindex = toindex;
				i++;
			}
		}// if
		return replaced;
	}

	public String str_replace_i(String src, String des, String org) {

		String org_upper = org.toUpperCase();
		String src_upper = src.toUpperCase();
		int fromindex = 0;
		int toindex = 0;
		String replaced = "";
		int i = 0;
		if ("".equals(src) || src == null) {
			replaced = org;
		} else {
			while (fromindex >= 0) {
				if (i == 0) {
					toindex = org_upper.indexOf(src_upper, 0);
					if (toindex < 0) {
						replaced = org.substring(0, org_upper.length());
						break;
					} else {
						replaced = org.substring(0, toindex);
						replaced += des;
					}
				} else {
					toindex = org_upper.indexOf(src_upper, fromindex
							+ src.length());
					if (toindex < 0) {
						replaced += org.substring(fromindex + src.length(), org
								.length());
						break;
					} else {
						replaced += org.substring(fromindex + src.length(),
								toindex);
						replaced += des;
					}
				}
				fromindex = toindex;
				i++;
			}
		}
		return replaced;

	}
	
	public String null2empty(String str) {
		if (str==null) str = "";
		return str;
	}
}
