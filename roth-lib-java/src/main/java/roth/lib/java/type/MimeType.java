package roth.lib.java.type;

public enum MimeType
{
	APPLICATION_ATOM_XML				("application/atom+xml"),
	APPLICATION_DART					("application/dart"),
	APPLICATION_ECMASCRIPT				("application/ecmascript"),
	APPLICATION_EDI_X12					("application/edi-x12"),
	APPLICATION_EDIFACT					("application/edifact"),
	APPLICATION_JSON					("application/json"),
	APPLICATION_JAVASCRIPT				("application/javascript"),
	APPLICATION_OCTECT_STREAM			("application/octet-stream"),
	APPLICATION_OGG						("application/ogg"),
	APPLICATION_DASH_XML				("application/dash+xml"),
	APPLICATION_PDF						("application/pdf"),
	APPLICATION_POSTSCRIPT				("application/postscript"),
	APPLICATION_RDF_XML					("application/rdf+xml"),
	APPLICATION_RSS_XML					("application/rss+xml"),
	APPLICATION_SOAP_XML				("application/soap+xml"),
	APPLICATION_FONT_WOFF				("application/font-woff"),
	APPLICATION_XHTML_XML				("application/xhtml+xml"),
	APPLICATION_XML						("application/xml"),
	APPLICATION_XML_DTD					("application/xml-dtd"),
	APPLICATION_XOP_XML					("application/xop+xml"),
	APPLICATION_ZIP						("application/zip"),
	APPLICATION_GZIP					("application/gzip"),
	APPLICATION_EXAMPLE					("application/example"),
	APPLICATION_X_NACL					("application/x-nacl"),
	APPLICATION_X_PNACL					("application/x-pnacl"),
	APPLICATION_X_WWW_FORM_URLENCODED	("application/x-www-form-urlencoded"),
	
	AUDIO_BASIC							("audio/basic"),
	AUDIO_L24							("audio/l24"),
	AUDIO_MP4							("audio/mp4"),
	AUDIO_MPEG							("audio/mpeg"),
	AUDIO_OGG							("audio/ogg"),
	AUDIO_FLAC							("audio/flac"),
	AUDIO_OPUS							("audio/opus"),
	AUDIO_VORBIS						("audio/vorbis"),
	AUDIO_VND_RN_REALAUDIO				("audio/vnd.rn-realaudio"),
	AUDIO_VND_WAVE						("audio/vnd.wave"),
	AUDIO_WEBM							("audio/webm"),
	AUDIO_EXAMPLE						("audio/example"),
	
	EXAMPLE								("example"),
	
	IMAGE_GIF							("image/gif"),
	IMAGE_JPEG							("image/jpeg"),
	IMAGE_PJPEG							("image/pjpeg"),
	IMAGE_PNG							("image/png"),
	IMAGE_SVG_XML						("image/svg+xml"),
	IMAGE_TIFF							("image/tiff"),
	IMAGE_VND_DJVU						("image/vnd.djvu"),
	IMAGE_EXAMPLE						("image/example"),
	
	MESSAGE_HTTP						("message/http"),
	MESSAGE_IMDN_XML					("message/imdn+xml"),
	MESSAGE_PARTIAL						("message/partial"),
	MESSAGE_RFC822						("message/rfc822"),
	MESSAGE_EXAMPLE						("message/example"),
	
	MODEL_IGES							("model/iges"),
	MODEL_MESH							("model/mesh"),
	MODEL_VRML							("model/vrml"),
	MODEL_X3D_BINARY					("model/x3d+binary"),
	MODEL_X3D_FASTINFOSET				("model/x3d+fastinfoset"),
	MODEL_X3D_VRML						("model/x3d-vrml"),
	MODEL_X3D_XML						("model/x3d+xml"),
	MODEL_EXAMPLE						("model/example"),
	
	MULTIPART_MIXED						("multipart/mixed"),
	MULTIPART_ALTERNATIVE				("multipart/alternative"),
	MULTIPART_RELATED					("multipart/related"),
	MULTIPART_FORM_DATA					("multipart/form-data"),
	MULTIPART_SIGNED					("multipart/signed"),
	MULTIPART_ENCRYPTED					("multipart/encrypted"),
	MULTIPART_EXAMPLE					("multipart/example"),
	
	TEXT_CMD							("text/cmd"),
	TEXT_CSS							("text/css"),
	TEXT_CSV							("text/csv"),
	TEXT_EXAMPLE						("text/example"),
	TEXT_HTML							("text/html"),
	TEXT_JAVASCRIPT						("text/javascript"),
	TEXT_PLAIN							("text/plain"),
	TEXT_RTF							("text/rtf"),
	TEXT_VCARD							("text/vcard"),
	TEXT_VND_ABC						("text/vnd.abc"),
	TEXT_XML							("text/xml"),
	
	VIDEO_AVI							("video/avi"),
	VIDEO_EXAMPLE						("video/example"),
	VIDEO_MPEG							("video/mpeg"),
	VIDEO_MP4							("video/mp4"),
	VIDEO_OGG							("video/ogg"),
	VIDEO_QUICKTIME						("video/quicktime"),
	VIDEO_WEBM							("video/webm"),
	VIDEO_X_MATROSKA					("video/x-matroska"),
	VIDEO_X_MS_WMV						("video/x-ms-wmv"),
	VIDEO_X_FLV							("video/x-flv"),
	;
	
	protected String value;
	
	MimeType(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
	public static MimeType fromString(String value)
	{
		MimeType mimeType = null;
		if(value != null)
		{
			switch(value.toLowerCase())
			{
				case "json":
				case "application/json":
				{
					mimeType =  MimeType.APPLICATION_JSON;
					break;
				}
				case "xml":
				case "application/xml":
				{
					mimeType =  MimeType.APPLICATION_XML;
					break;
				}
				case "form":
				case "application/x-www-form-urlencoded":
				{
					mimeType =  MimeType.APPLICATION_X_WWW_FORM_URLENCODED;
					break;
				}
				case "multipart-form":
				case "multipart/form-data":
				{
					mimeType =  MimeType.MULTIPART_FORM_DATA;
					break;
				}
			}
			if(mimeType == null)
			{
				for(MimeType tempMimeType : values())
				{
					if(tempMimeType.value.equalsIgnoreCase(value))
					{
						mimeType = tempMimeType;
						break;
					}
				}
			}
		}
		return mimeType;
	}
	
}
