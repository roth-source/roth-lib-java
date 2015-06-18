package roth.lib;

public interface Characters
{
	char NULL					= '\u0000';	// 000
	char START_OF_HEADING		= '\u0001';	// 001
	char START_OF_TEXT			= '\u0002';	// 002
	char END_OF_TEXT			= '\u0003';	// 003
	char END_OF_TRANSMISSION	= '\u0004';	// 004
	char ENQUIRY				= '\u0005';	// 005
	char ACKNOWLEDGE			= '\u0006';	// 006
	char BELL					= '\u0007';	// 007
	char BACKSPACE				= '\b';		// 008
	char TAB					= '\t';		// 009
	char NEW_LINE				= '\n';		// 010
	char VERTICAL_TAB			= '\u000B';	// 011
	char FORM_FEED				= '\f';		// 012
	char CARRIAGE_RETURN		= '\r';		// 013
	char SHIFT_OUT				= '\u000E';	// 014
	char SHIFT_IN				= '\u000F';	// 015
	char DATA_LINK_ESCAPE		= '\u0010';	// 016
	char DEVICE_CONTROL_1		= '\u0011';	// 017
	char DEVICE_CONTROL_2		= '\u0012';	// 018
	char DEVICE_CONTROL_3		= '\u0013';	// 019
	char DEVICE_CONTROL_4		= '\u0014';	// 020
	char NEGATIVE_ACKNOWLEDGE	= '\u0015';	// 021
	char SYNCHRONOUS_IDLE		= '\u0016';	// 022
	char END_OF_BLOCK			= '\u0017';	// 023
	char CANCEL					= '\u0018';	// 024
	char END_OF_MEDIUM			= '\u0019';	// 025
	char SUBSTITUTE				= '\u001A';	// 026
	char ESCAPE					= '\u001B';	// 027
	char FILE_SEPERATOR			= '\u001C';	// 028
	char GROUP_SEPERATOR		= '\u001D';	// 029
	char RECORD_SEPERATOR		= '\u001E';	// 030
	char UNIT_SEPERATOR			= '\u001F';	// 031
	char SPACE					= ' ';		// 032
	char EXCLAMATION			= '!';		// 033
	char QUOTE					= '"';		// 034
	char HASH					= '#';		// 035
	char DOLLAR					= '$';		// 036
	char PERCENT				= '%';		// 037
	char AMPERSAND				= '&';		// 038
	char SINGLE_QUOTE			= '\'';		// 039
	char LEFT_PAREN				= '(';		// 040
	char RIGHT_PAREN			= ')';		// 041
	char ASTERISK				= '*';		// 042
	char PLUS					= '+';		// 043
	char COMMA					= ',';		// 044
	char DASH					= '-';		// 045
	char DOT					= '.';		// 046
	char SLASH					= '/';		// 047
	char _0						= '0';		// 048
	char _1						= '1';		// 049
	char _2						= '2';		// 050
	char _3						= '3';		// 051
	char _4						= '4';		// 052
	char _5						= '5';		// 053
	char _6						= '6';		// 054
	char _7						= '7';		// 055
	char _8						= '8';		// 056
	char _9						= '9';		// 057
	char COLON					= ':';		// 058
	char SEMI_COLON				= ';';		// 059
	char LEFT_ANGLE_BRACKET		= '<';		// 060
	char EQUAL					= '=';		// 061
	char RIGHT_ANGLE_BRACKET	= '>';		// 062
	char QUESTION				= '?';		// 063
	char AT						= '@';		// 064
	char A						= 'A';		// 065
	char B						= 'B';		// 066
	char C						= 'C';		// 067
	char D						= 'D';		// 068
	char E						= 'E';		// 069
	char F						= 'F';		// 070
	char G						= 'G';		// 071
	char H						= 'H';		// 072
	char I						= 'I';		// 073
	char J						= 'J';		// 074
	char K						= 'K';		// 075
	char L						= 'L';		// 076
	char M						= 'M';		// 077
	char N						= 'N';		// 078
	char O						= 'O';		// 079
	char P						= 'P';		// 080
	char Q						= 'Q';		// 081
	char R						= 'R';		// 082
	char S						= 'S';		// 083
	char T						= 'T';		// 084
	char U						= 'U';		// 085
	char V						= 'V';		// 086
	char W						= 'W';		// 087
	char X						= 'X';		// 088
	char Y						= 'Y';		// 089
	char Z						= 'Z';		// 090
	char LEFT_BRACKET			= '[';		// 091
	char BACKSLASH				= '\\'; 	// 092
	char RIGHT_BRACKET			= ']';		// 093
	char CARET					= '^';		// 094
	char UNDERSCORE				= '_';		// 095
	char GRAVE					= '`';		// 096
	char a						= 'a';		// 097
	char b						= 'b';		// 098
	char c						= 'c';		// 099
	char d						= 'd';		// 100
	char e						= 'e';		// 101
	char f						= 'f';		// 102
	char g						= 'g';		// 103
	char h						= 'h';		// 104
	char i						= 'i';		// 105
	char j						= 'j';		// 106
	char k						= 'k';		// 107
	char l						= 'l';		// 108
	char m						= 'm';		// 109
	char n						= 'n';		// 110
	char o						= 'o';		// 111
	char p						= 'p';		// 112
	char q						= 'q';		// 113
	char r						= 'r';		// 114
	char s						= 's';		// 115
	char t						= 't';		// 116
	char u						= 'u';		// 117
	char v						= 'v';		// 118
	char w						= 'w';		// 119
	char x						= 'x';		// 120
	char y						= 'y';		// 121
	char z						= 'z';		// 122
	char LEFT_BRACE				= '{';		// 123
	char BAR					= '|';		// 124
	char RIGHT_BRACE			= '}';		// 125
	char TILDE					= '~';		// 126
	char DELETE					= '\u007F';	// 127
	
	String BLANK				= "";
	String INLINE_COMMENT		= "//";
	String OPEN_DOC_COMMENT		= "/**";
	String OPEN_BLOCK_COMMENT	= "/*";
	String CLOSE_BLOCK_COMMENT	= "*/";
	String REFERENCE			= "::";
	String LAMBDA				= "->";
	String COMMA_SEPERATOR		= ", ";
	String VARARGS				= "...";
	
}
