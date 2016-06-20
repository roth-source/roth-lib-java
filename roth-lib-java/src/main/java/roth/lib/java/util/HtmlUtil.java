package roth.lib.java.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map.Entry;

import roth.lib.java.Characters;
import roth.lib.java.lang.Map;

public class HtmlUtil implements Characters
{
	public static Map<Character, String> ESCAPE_CHARACTER_MAP = new Map<>();
	public static Map<String, Character> UNESCAPE_CHARACTER_MAP = new Map<>();
	
	static
	{
		ESCAPE_CHARACTER_MAP.put(QUOTE, "&quot;");
		ESCAPE_CHARACTER_MAP.put(SINGLE_QUOTE, "&apos;");
		ESCAPE_CHARACTER_MAP.put(AMPERSAND, "&amp;");
		ESCAPE_CHARACTER_MAP.put(LEFT_ANGLE_BRACKET, "&lt;");
		ESCAPE_CHARACTER_MAP.put(RIGHT_ANGLE_BRACKET, "&gt;");
		ESCAPE_CHARACTER_MAP.put('\u00A0', "&nbsp;");
		ESCAPE_CHARACTER_MAP.put('\u00A1', "&iexcl;");
		ESCAPE_CHARACTER_MAP.put('\u00A2', "&cent;");
		ESCAPE_CHARACTER_MAP.put('\u00A3', "&pound;");
		ESCAPE_CHARACTER_MAP.put('\u00A4', "&curren;");
		ESCAPE_CHARACTER_MAP.put('\u00A5', "&yen;");
		ESCAPE_CHARACTER_MAP.put('\u00A6', "&brvbar;");
		ESCAPE_CHARACTER_MAP.put('\u00A7', "&sect;");
		ESCAPE_CHARACTER_MAP.put('\u00A8', "&uml;");
		ESCAPE_CHARACTER_MAP.put('\u00A9', "&copy;");
		ESCAPE_CHARACTER_MAP.put('\u00AA', "&ordf;");
		ESCAPE_CHARACTER_MAP.put('\u00AB', "&laquo;");
		ESCAPE_CHARACTER_MAP.put('\u00AC', "&not;");
		ESCAPE_CHARACTER_MAP.put('\u00AD', "&shy;");
		ESCAPE_CHARACTER_MAP.put('\u00AE', "&reg;");
		ESCAPE_CHARACTER_MAP.put('\u00AF', "&macr;");
		ESCAPE_CHARACTER_MAP.put('\u00B0', "&deg;");
		ESCAPE_CHARACTER_MAP.put('\u00B1', "&plusmn;");
		ESCAPE_CHARACTER_MAP.put('\u00B2', "&sup2;");
		ESCAPE_CHARACTER_MAP.put('\u00B3', "&sup3;");
		ESCAPE_CHARACTER_MAP.put('\u00B4', "&acute;");
		ESCAPE_CHARACTER_MAP.put('\u00B5', "&micro;");
		ESCAPE_CHARACTER_MAP.put('\u00B6', "&para;");
		ESCAPE_CHARACTER_MAP.put('\u00B7', "&middot;");
		ESCAPE_CHARACTER_MAP.put('\u00B8', "&cedil;");
		ESCAPE_CHARACTER_MAP.put('\u00B9', "&sup1;");
		ESCAPE_CHARACTER_MAP.put('\u00BA', "&ordm;");
		ESCAPE_CHARACTER_MAP.put('\u00BB', "&raquo;");
		ESCAPE_CHARACTER_MAP.put('\u00BC', "&frac14;");
		ESCAPE_CHARACTER_MAP.put('\u00BD', "&frac12;");
		ESCAPE_CHARACTER_MAP.put('\u00BE', "&frac34;");
		ESCAPE_CHARACTER_MAP.put('\u00BF', "&iquest;");
		ESCAPE_CHARACTER_MAP.put('\u00C0', "&Agrave;");
		ESCAPE_CHARACTER_MAP.put('\u00C1', "&Aacute;");
		ESCAPE_CHARACTER_MAP.put('\u00C2', "&Acirc;");
		ESCAPE_CHARACTER_MAP.put('\u00C3', "&Atilde;");
		ESCAPE_CHARACTER_MAP.put('\u00C4', "&Auml;");
		ESCAPE_CHARACTER_MAP.put('\u00C5', "&Aring;");
		ESCAPE_CHARACTER_MAP.put('\u00C6', "&AElig;");
		ESCAPE_CHARACTER_MAP.put('\u00C7', "&Ccedil;");
		ESCAPE_CHARACTER_MAP.put('\u00C8', "&Egrave;");
		ESCAPE_CHARACTER_MAP.put('\u00C9', "&Eacute;");
		ESCAPE_CHARACTER_MAP.put('\u00CA', "&Ecirc;");
		ESCAPE_CHARACTER_MAP.put('\u00CB', "&Euml;");
		ESCAPE_CHARACTER_MAP.put('\u00CC', "&Igrave;");
		ESCAPE_CHARACTER_MAP.put('\u00CD', "&Iacute;");
		ESCAPE_CHARACTER_MAP.put('\u00CE', "&Icirc;");
		ESCAPE_CHARACTER_MAP.put('\u00CF', "&Iuml;");
		ESCAPE_CHARACTER_MAP.put('\u00D0', "&ETH;");
		ESCAPE_CHARACTER_MAP.put('\u00D1', "&Ntilde;");
		ESCAPE_CHARACTER_MAP.put('\u00D2', "&Ograve;");
		ESCAPE_CHARACTER_MAP.put('\u00D3', "&Oacute;");
		ESCAPE_CHARACTER_MAP.put('\u00D4', "&Ocirc;");
		ESCAPE_CHARACTER_MAP.put('\u00D5', "&Otilde;");
		ESCAPE_CHARACTER_MAP.put('\u00D6', "&Ouml;");
		ESCAPE_CHARACTER_MAP.put('\u00D7', "&times;");
		ESCAPE_CHARACTER_MAP.put('\u00D8', "&Oslash;");
		ESCAPE_CHARACTER_MAP.put('\u00D9', "&Ugrave;");
		ESCAPE_CHARACTER_MAP.put('\u00DA', "&Uacute;");
		ESCAPE_CHARACTER_MAP.put('\u00DB', "&Ucirc;");
		ESCAPE_CHARACTER_MAP.put('\u00DC', "&Uuml;");
		ESCAPE_CHARACTER_MAP.put('\u00DD', "&Yacute;");
		ESCAPE_CHARACTER_MAP.put('\u00DE', "&THORN;");
		ESCAPE_CHARACTER_MAP.put('\u00DF', "&szlig;");
		ESCAPE_CHARACTER_MAP.put('\u00E0', "&agrave;");
		ESCAPE_CHARACTER_MAP.put('\u00E1', "&aacute;");
		ESCAPE_CHARACTER_MAP.put('\u00E2', "&acirc;");
		ESCAPE_CHARACTER_MAP.put('\u00E3', "&atilde;");
		ESCAPE_CHARACTER_MAP.put('\u00E4', "&auml;");
		ESCAPE_CHARACTER_MAP.put('\u00E5', "&aring;");
		ESCAPE_CHARACTER_MAP.put('\u00E6', "&aelig;");
		ESCAPE_CHARACTER_MAP.put('\u00E7', "&ccedil;");
		ESCAPE_CHARACTER_MAP.put('\u00E8', "&egrave;");
		ESCAPE_CHARACTER_MAP.put('\u00E9', "&eacute;");
		ESCAPE_CHARACTER_MAP.put('\u00EA', "&ecirc;");
		ESCAPE_CHARACTER_MAP.put('\u00EB', "&euml;");
		ESCAPE_CHARACTER_MAP.put('\u00EC', "&igrave;");
		ESCAPE_CHARACTER_MAP.put('\u00ED', "&iacute;");
		ESCAPE_CHARACTER_MAP.put('\u00EE', "&icirc;");
		ESCAPE_CHARACTER_MAP.put('\u00EF', "&iuml;");
		ESCAPE_CHARACTER_MAP.put('\u00F0', "&eth;");
		ESCAPE_CHARACTER_MAP.put('\u00F1', "&ntilde;");
		ESCAPE_CHARACTER_MAP.put('\u00F2', "&ograve;");
		ESCAPE_CHARACTER_MAP.put('\u00F3', "&oacute;");
		ESCAPE_CHARACTER_MAP.put('\u00F4', "&ocirc;");
		ESCAPE_CHARACTER_MAP.put('\u00F5', "&otilde;");
		ESCAPE_CHARACTER_MAP.put('\u00F6', "&ouml;");
		ESCAPE_CHARACTER_MAP.put('\u00F7', "&divide;");
		ESCAPE_CHARACTER_MAP.put('\u00F8', "&oslash;");
		ESCAPE_CHARACTER_MAP.put('\u00F9', "&ugrave;");
		ESCAPE_CHARACTER_MAP.put('\u00FA', "&uacute;");
		ESCAPE_CHARACTER_MAP.put('\u00FB', "&ucirc;");
		ESCAPE_CHARACTER_MAP.put('\u00FC', "&uuml;");
		ESCAPE_CHARACTER_MAP.put('\u00FD', "&yacute;");
		ESCAPE_CHARACTER_MAP.put('\u00FE', "&thorn;");
		ESCAPE_CHARACTER_MAP.put('\u00FF', "&yuml;");
		ESCAPE_CHARACTER_MAP.put('\u0192', "&fnof;");
		ESCAPE_CHARACTER_MAP.put('\u0391', "&Alpha;");
		ESCAPE_CHARACTER_MAP.put('\u0392', "&Beta;");
		ESCAPE_CHARACTER_MAP.put('\u0393', "&Gamma;");
		ESCAPE_CHARACTER_MAP.put('\u0394', "&Delta;");
		ESCAPE_CHARACTER_MAP.put('\u0395', "&Epsilon;");
		ESCAPE_CHARACTER_MAP.put('\u0396', "&Zeta;");
		ESCAPE_CHARACTER_MAP.put('\u0397', "&Eta;");
		ESCAPE_CHARACTER_MAP.put('\u0398', "&Theta;");
		ESCAPE_CHARACTER_MAP.put('\u0399', "&Iota;");
		ESCAPE_CHARACTER_MAP.put('\u039A', "&Kappa;");
		ESCAPE_CHARACTER_MAP.put('\u039B', "&Lambda;");
		ESCAPE_CHARACTER_MAP.put('\u039C', "&Mu;");
		ESCAPE_CHARACTER_MAP.put('\u039D', "&Nu;");
		ESCAPE_CHARACTER_MAP.put('\u039E', "&Xi;");
		ESCAPE_CHARACTER_MAP.put('\u039F', "&Omicron;");
		ESCAPE_CHARACTER_MAP.put('\u03A0', "&Pi;");
		ESCAPE_CHARACTER_MAP.put('\u03A1', "&Rho;");
		ESCAPE_CHARACTER_MAP.put('\u03A3', "&Sigma;");
		ESCAPE_CHARACTER_MAP.put('\u03A4', "&Tau;");
		ESCAPE_CHARACTER_MAP.put('\u03A5', "&Upsilon;");
		ESCAPE_CHARACTER_MAP.put('\u03A6', "&Phi;");
		ESCAPE_CHARACTER_MAP.put('\u03A7', "&Chi;");
		ESCAPE_CHARACTER_MAP.put('\u03A8', "&Psi;");
		ESCAPE_CHARACTER_MAP.put('\u03A9', "&Omega;");
		ESCAPE_CHARACTER_MAP.put('\u03B1', "&alpha;");
		ESCAPE_CHARACTER_MAP.put('\u03B2', "&beta;");
		ESCAPE_CHARACTER_MAP.put('\u03B3', "&gamma;");
		ESCAPE_CHARACTER_MAP.put('\u03B4', "&delta;");
		ESCAPE_CHARACTER_MAP.put('\u03B5', "&epsilon;");
		ESCAPE_CHARACTER_MAP.put('\u03B6', "&zeta;");
		ESCAPE_CHARACTER_MAP.put('\u03B7', "&eta;");
		ESCAPE_CHARACTER_MAP.put('\u03B8', "&theta;");
		ESCAPE_CHARACTER_MAP.put('\u03B9', "&iota;");
		ESCAPE_CHARACTER_MAP.put('\u03BA', "&kappa;");
		ESCAPE_CHARACTER_MAP.put('\u03BB', "&lambda;");
		ESCAPE_CHARACTER_MAP.put('\u03BC', "&mu;");
		ESCAPE_CHARACTER_MAP.put('\u03BD', "&nu;");
		ESCAPE_CHARACTER_MAP.put('\u03BE', "&xi;");
		ESCAPE_CHARACTER_MAP.put('\u03BF', "&omicron;");
		ESCAPE_CHARACTER_MAP.put('\u03C0', "&pi;");
		ESCAPE_CHARACTER_MAP.put('\u03C1', "&rho;");
		ESCAPE_CHARACTER_MAP.put('\u03C2', "&sigmaf;");
		ESCAPE_CHARACTER_MAP.put('\u03C3', "&sigma;");
		ESCAPE_CHARACTER_MAP.put('\u03C4', "&tau;");
		ESCAPE_CHARACTER_MAP.put('\u03C5', "&upsilon;");
		ESCAPE_CHARACTER_MAP.put('\u03C6', "&phi;");
		ESCAPE_CHARACTER_MAP.put('\u03C7', "&chi;");
		ESCAPE_CHARACTER_MAP.put('\u03C8', "&psi;");
		ESCAPE_CHARACTER_MAP.put('\u03C9', "&omega;");
		ESCAPE_CHARACTER_MAP.put('\u03D1', "&thetasym;");
		ESCAPE_CHARACTER_MAP.put('\u03D2', "&upsih;");
		ESCAPE_CHARACTER_MAP.put('\u03D6', "&piv;");
		ESCAPE_CHARACTER_MAP.put('\u2022', "&bull;");
		ESCAPE_CHARACTER_MAP.put('\u2026', "&hellip;");
		ESCAPE_CHARACTER_MAP.put('\u2032', "&prime;");
		ESCAPE_CHARACTER_MAP.put('\u2033', "&Prime;");
		ESCAPE_CHARACTER_MAP.put('\u203E', "&oline;");
		ESCAPE_CHARACTER_MAP.put('\u2044', "&frasl;");
		ESCAPE_CHARACTER_MAP.put('\u2118', "&weierp;");
		ESCAPE_CHARACTER_MAP.put('\u2111', "&image;");
		ESCAPE_CHARACTER_MAP.put('\u211C', "&real;");
		ESCAPE_CHARACTER_MAP.put('\u2122', "&trade;");
		ESCAPE_CHARACTER_MAP.put('\u2135', "&alefsym;");
		ESCAPE_CHARACTER_MAP.put('\u2190', "&larr;");
		ESCAPE_CHARACTER_MAP.put('\u2191', "&uarr;");
		ESCAPE_CHARACTER_MAP.put('\u2192', "&rarr;");
		ESCAPE_CHARACTER_MAP.put('\u2193', "&darr;");
		ESCAPE_CHARACTER_MAP.put('\u2194', "&harr;");
		ESCAPE_CHARACTER_MAP.put('\u21B5', "&crarr;");
		ESCAPE_CHARACTER_MAP.put('\u21D0', "&lArr;");
		ESCAPE_CHARACTER_MAP.put('\u21D1', "&uArr;");
		ESCAPE_CHARACTER_MAP.put('\u21D2', "&rArr;");
		ESCAPE_CHARACTER_MAP.put('\u21D3', "&dArr;");
		ESCAPE_CHARACTER_MAP.put('\u21D4', "&hArr;");
		ESCAPE_CHARACTER_MAP.put('\u2200', "&forall;");
		ESCAPE_CHARACTER_MAP.put('\u2202', "&part;");
		ESCAPE_CHARACTER_MAP.put('\u2203', "&exist;");
		ESCAPE_CHARACTER_MAP.put('\u2205', "&empty;");
		ESCAPE_CHARACTER_MAP.put('\u2207', "&nabla;");
		ESCAPE_CHARACTER_MAP.put('\u2208', "&isin;");
		ESCAPE_CHARACTER_MAP.put('\u2209', "&notin;");
		ESCAPE_CHARACTER_MAP.put('\u220B', "&ni;");
		ESCAPE_CHARACTER_MAP.put('\u220F', "&prod;");
		ESCAPE_CHARACTER_MAP.put('\u2211', "&sum;");
		ESCAPE_CHARACTER_MAP.put('\u2212', "&minus;");
		ESCAPE_CHARACTER_MAP.put('\u2217', "&lowast;");
		ESCAPE_CHARACTER_MAP.put('\u221A', "&radic;");
		ESCAPE_CHARACTER_MAP.put('\u221D', "&prop;");
		ESCAPE_CHARACTER_MAP.put('\u221E', "&infin;");
		ESCAPE_CHARACTER_MAP.put('\u2220', "&ang;");
		ESCAPE_CHARACTER_MAP.put('\u2227', "&and;");
		ESCAPE_CHARACTER_MAP.put('\u2228', "&or;");
		ESCAPE_CHARACTER_MAP.put('\u2229', "&cap;");
		ESCAPE_CHARACTER_MAP.put('\u222A', "&cup;");
		ESCAPE_CHARACTER_MAP.put('\u222B', "&int;");
		ESCAPE_CHARACTER_MAP.put('\u2234', "&there4;");
		ESCAPE_CHARACTER_MAP.put('\u223C', "&sim;");
		ESCAPE_CHARACTER_MAP.put('\u2245', "&cong;");
		ESCAPE_CHARACTER_MAP.put('\u2248', "&asymp;");
		ESCAPE_CHARACTER_MAP.put('\u2260', "&ne;");
		ESCAPE_CHARACTER_MAP.put('\u2261', "&equiv;");
		ESCAPE_CHARACTER_MAP.put('\u2264', "&le;");
		ESCAPE_CHARACTER_MAP.put('\u2265', "&ge;");
		ESCAPE_CHARACTER_MAP.put('\u2282', "&sub;");
		ESCAPE_CHARACTER_MAP.put('\u2283', "&sup;");
		ESCAPE_CHARACTER_MAP.put('\u2286', "&sube;");
		ESCAPE_CHARACTER_MAP.put('\u2287', "&supe;");
		ESCAPE_CHARACTER_MAP.put('\u2295', "&oplus;");
		ESCAPE_CHARACTER_MAP.put('\u2297', "&otimes;");
		ESCAPE_CHARACTER_MAP.put('\u22A5', "&perp;");
		ESCAPE_CHARACTER_MAP.put('\u22C5', "&sdot;");
		ESCAPE_CHARACTER_MAP.put('\u2308', "&lceil;");
		ESCAPE_CHARACTER_MAP.put('\u2309', "&rceil;");
		ESCAPE_CHARACTER_MAP.put('\u230A', "&lfloor;");
		ESCAPE_CHARACTER_MAP.put('\u230B', "&rfloor;");
		ESCAPE_CHARACTER_MAP.put('\u2329', "&lang;");
		ESCAPE_CHARACTER_MAP.put('\u232A', "&rang;");
		ESCAPE_CHARACTER_MAP.put('\u25CA', "&loz;");
		ESCAPE_CHARACTER_MAP.put('\u2660', "&spades;");
		ESCAPE_CHARACTER_MAP.put('\u2663', "&clubs;");
		ESCAPE_CHARACTER_MAP.put('\u2665', "&hearts;");
		ESCAPE_CHARACTER_MAP.put('\u2666', "&diams;");
		ESCAPE_CHARACTER_MAP.put('\u0152', "&OElig;");
		ESCAPE_CHARACTER_MAP.put('\u0153', "&oelig;");
		ESCAPE_CHARACTER_MAP.put('\u0160', "&Scaron;");
		ESCAPE_CHARACTER_MAP.put('\u0161', "&scaron;");
		ESCAPE_CHARACTER_MAP.put('\u0178', "&Yuml;");
		ESCAPE_CHARACTER_MAP.put('\u02C6', "&circ;");
		ESCAPE_CHARACTER_MAP.put('\u02DC', "&tilde;");
		ESCAPE_CHARACTER_MAP.put('\u2002', "&ensp;");
		ESCAPE_CHARACTER_MAP.put('\u2003', "&emsp;");
		ESCAPE_CHARACTER_MAP.put('\u2009', "&thinsp;");
		ESCAPE_CHARACTER_MAP.put('\u200C', "&zwnj;");
		ESCAPE_CHARACTER_MAP.put('\u200D', "&zwj;");
		ESCAPE_CHARACTER_MAP.put('\u200E', "&lrm;");
		ESCAPE_CHARACTER_MAP.put('\u200F', "&rlm;");
		ESCAPE_CHARACTER_MAP.put('\u2013', "&ndash;");
		ESCAPE_CHARACTER_MAP.put('\u2014', "&mdash;");
		ESCAPE_CHARACTER_MAP.put('\u2018', "&lsquo;");
		ESCAPE_CHARACTER_MAP.put('\u2019', "&rsquo;");
		ESCAPE_CHARACTER_MAP.put('\u201A', "&sbquo;");
		ESCAPE_CHARACTER_MAP.put('\u201C', "&ldquo;");
		ESCAPE_CHARACTER_MAP.put('\u201D', "&rdquo;");
		ESCAPE_CHARACTER_MAP.put('\u201E', "&bdquo;");
		ESCAPE_CHARACTER_MAP.put('\u2020', "&dagger;");
		ESCAPE_CHARACTER_MAP.put('\u2021', "&Dagger;");
		ESCAPE_CHARACTER_MAP.put('\u2030', "&permil;");
		ESCAPE_CHARACTER_MAP.put('\u2039', "&lsaquo;");
		ESCAPE_CHARACTER_MAP.put('\u203A', "&rsaquo;");
		ESCAPE_CHARACTER_MAP.put('\u20AC', "&euro;");
		
		for(Entry<Character, String> escapeCharacterEntry : ESCAPE_CHARACTER_MAP.entrySet())
		{
			UNESCAPE_CHARACTER_MAP.put(escapeCharacterEntry.getValue(), escapeCharacterEntry.getKey());
		}
	}
	
	protected HtmlUtil()
	{
		
	}
	
	public static String escape(String value)
	{
		try
		{
			return escape(new StringReader(value));
		}
		catch(IOException e)
		{
			return value;
		}
	}
	
	public static String escape(Reader reader) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		char c;
		while((b = reader.read()) != -1)
		{
			c = (char) b;
			String encoding = ESCAPE_CHARACTER_MAP.get(c);
			if(encoding != null)
			{
				builder.append(encoding);
			}
			else
			{
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	public static String unescape(String value)
	{
		try
		{
			return unescape(new StringReader(value));
		}
		catch(IOException e)
		{
			return value;
		}
	}
	
	public static String unescape(Reader reader) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		char c;
		while((b = reader.read()) != -1)
		{
			c = (char) b;
			if(AMPERSAND == c)
			{
				StringBuilder escapedBuilder = new StringBuilder();
				escapedBuilder.append(AMPERSAND);
				while((b = reader.read()) != -1 && (c = (char) b) != SEMI_COLON)
				{
					escapedBuilder.append(c);
				}	
				escapedBuilder.append(SEMI_COLON);
				Character unescapedCharacter = UNESCAPE_CHARACTER_MAP.get(escapedBuilder.toString());
				if(unescapedCharacter != null)
				{
					builder.append(unescapedCharacter);
				}
			}
			else
			{
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	public static String strip(String html)
	{
		return html != null ? html.replaceAll("<\\/?[^>]+(>|$)", "") : null;
	}
	
	public static void main(String[] args)
	{
		String value = escape("<test value=\"blah\">asdf</test>");
		System.out.println(value);
		System.out.println(unescape(value));
	}
	
}
