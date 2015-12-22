var roth = roth || {};
roth.lib = roth.lib || {};
roth.lib.js = roth.lib.js || {};
roth.lib.js.version = "0.1.5-SNAPSHOT";



/**
 * === undefined
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isUndefined = isUndefined || function(value)
{
	return value === undefined;
};

/**
 * not undefined
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isDefined = isDefined || function(value)
{
	return !isUndefined(value);
};

/**
 * === null
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isNull = isNull || function(value)
{
	return value === null;
};

/**
 * not undefined and not null
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isSet = isSet || function(value)
{
	return !isUndefined(value) && !isNull(value);
};

/**
 * is set and is not blank
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isValid = isValid || function(value)
{
	return isSet(value) && value !== "";
};

/**
 * is valid and is string
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isValidString = isValidString || function(value)
{
	return isValid(value) && isString(value);
};

/**
 * is not valid
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isInvalid = isInvalid || function(value)
{
	return !isValid(value);
};

/**
 * is set and is invalid string or empty array/object
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isEmpty = isEmpty || function(value)
{
	var empty = !isSet(value);
	if(!empty)
	{
		if(isString(value))
		{
			empty = !isValidString(value);
		}
		else if(isArray(value))
		{
			empty = value.length == 0;
		}
		else if(isObject(value))
		{
			empty = Object.keys(value).length == 0;
		}
	}
	return empty;
};

/**
 * is not empty
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isNotEmpty = isNotEmpty || function(value)
{
	return !isEmpty(value);
}

/**
 * is true or "true"
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isTrue = isTrue || function(value)
{
	return value === true || value === "true";
};

/**
 * is false or "false"
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isFalse = isFalse || function(value)
{
	return value === false || value === "false";;
};

/**
 * is type boolean
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isBoolean = isBoolean || function(value)
{
	return typeof value === "boolean";
};

/**
 * is type number
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isNumber = isNumber || function(value)
{
	return typeof value === "number";
};

/**
 * is type string
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isString = isString || function(value)
{
	return typeof value === "string";
};

/**
 * is type array
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isArray = isArray || function(value)
{
	return Array.isArray(value);
};

/**
 * is type function
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isFunction = isFunction || function(value)
{
	return typeof value === "function";
};

/**
 * is type date
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isDate = isDate || function(value)
{
	return value instanceof Date;
};

/**
 * is type error
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isError = isError || function(value)
{
	return value instanceof Error;
};

/**
 * is type regexp
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isRegExp = isRegExp || function(value)
{
	return value instanceof RegExp;
};

/**
 * is type object
 * @function
 * @param {*} value
 * @returns {Boolean}
 */
var isObject = isObject || function(value)
{
	return isSet(value) && typeof value === "object";
};

/**
 * is element in array
 * @function
 * @param {*} value
 * @param {Array} array
 * @returns {Boolean}
 */
var inArray = inArray || function(value, array)
{
	var contains = false;
	if(isArray(array))
	{
		contains = array.indexOf(value) > -1;
	}
	return contains;
};

/**
 * is element in map
 * @function
 * @param {*} value
 * @param {Object} map
 * @returns {Boolean}
 */
var inMap = inMap || function(value, map)
{
	var array = [];
	for(var key in map)
	{
		array.push(map[key]);
	}
	return inArray(value, array);
};

/**
 * foor loop convenience
 * @param {Object|Array} object
 * @param {Function} callback
 * @function
 */
var forEach = forEach || function(object, callback)
{
	if(isFunction(callback))
	{
		if(isArray(object))
		{
			for(var i in object)
			{
				var loop =
				{
					index 	: i,
					length 	: object.length,
					first	: i == 0,
					last	: i == object.length - 1
				};
				if(isFalse(callback(object[i], i, loop)))
				{
					break;
				}
			}
		}
		else if(isObject(object))
		{
			var keys = Object.keys(object);
			for(var i in keys)
			{
				var key = keys[i];
				var loop =
				{
					index 	: i,
					length 	: keys.length,
					first	: i == 0,
					last	: i == keys.length - 1
				};
				if(isFalse(callback(object[key], key, loop)))
				{
					break;
				}
			}
		}
	}
};





/**
 * A utility for manipulating cookies.
 * @namespace CookieUtil
 */
var CookieUtil = CookieUtil ||
{
	
	/**
	 * Gets the cookie value of the specified key.
	 * @memberof CookieUtil
	 * @method
	 * @param {String} key
	 * @returns {String}
	 */
	get : function(key)
	{
		return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + encodeURIComponent(key).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
	},
	
	/**
	 * Sets the cookie value of the specified key.
	 * @memberof CookieUtil
	 * @method
	 * @param {String} key
	 * @param {String} value
	 * @param {Number|String|Date} [end]
	 * @param {String} [path]
	 * @param {String} [domain]
	 * @param {Boolean} [secure]
	 * @returns {Boolean}
	 */
	set : function(key, value, end, path, domain, secure)
	{
		if(!key || /^(?:expires|max\-age|path|domain|secure)$/i.test(key)) { return false; }
		var expires = "";
		if(end)
		{
			switch(end.constructor)
			{
				case Number:
					expires = end === Infinity ? "; expires=Fri, 31 Dec 9999 23:59:59 GMT" : "; max-age=" + end;
					break;
				case String:
					expires = "; expires=" + end;
					break;
				case Date:
					expires = "; expires=" + end.toUTCString();
					break;
			}
		}
		document.cookie = encodeURIComponent(key) + "=" + encodeURIComponent(value) + expires + (domain ? "; domain=" + domain : "") + (path ? "; path=" + path : "") + (secure ? "; secure" : "");
		return true;
	},
	
	/**
	 * Removes the cookie value of the specified key.
	 * @memberof CookieUtil
	 * @method
	 * @param {String} key
	 * @param {String} [path]
	 * @param {String} [domain]
	 * @returns {Boolean}
	 */
	remove : function(key, path, domain)
	{
		if(!key || !this.has(key)) { return false; }
		document.cookie = encodeURIComponent(key) + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT" + ( domain ? "; domain=" + domain : "") + ( path ? "; path=" + path : "");
		return true;
	},
	
	/**
	 * Checks for existence of a cookie with specified key.
	 * @memberof CookieUtil
	 * @method
	 * @param {String} key
	 * @returns {Boolean}
	 */
	has : function(key)
	{
		return (new RegExp("(?:^|;\\s*)" + encodeURIComponent(key).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=")).test(document.cookie);
	}
	
};





/**
 * A utility for formatting currency.
 * @namespace CurrencyUtil
 */
var CurrencyUtil = CurrencyUtil ||
{
	
	/**
	 * Formats currency cents for input fields.
	 * @memberof CurrencyUtil
	 * @method
	 * @param {Number} value
	 * @returns {String}
	 */
	formatInput : function(value)
	{
		return this.format(value, null, null)
	},
	
	/**
	 * Formats currency cents into display text.
	 * @memberof CurrencyUtil
	 * @method
	 * @param {Number} value
	 * @returns {String}
	 */
	formatText : function(value)
	{
		return this.format(value, "$", ",")
	},
	
	/**
	 * Formats currency cents with prefix symbol and thousands seperator.
	 * @memberof CurrencyUtil
	 * @method
	 * @param {Number} value
	 * @param {String} [symbol]
	 * @param {String} [seperator]
	 * @returns {String}
	 */
	format : function(value, symbol, seperator)
	{
		if(isNumber(value))
		{
			value = value / 100;
			var formattedValue = isValidString(symbol) ? symbol : "";
			formattedValue += isValidString(seperator) ? parseFloat(value).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, seperator) : parseFloat(value).toFixed(2);
			return formattedValue;
		}
		else
		{
			return "";
		}
	},
	
	/**
	 * Parses currency string into a currency cents number.
	 * @memberof CurrencyUtil
	 * @method
	 * @param {String} value
	 * @returns {Number}
	 */
	parse : function(value)
	{
		var parsedValue = null;
		if(isValidString(value))
		{
			try
			{
				value = parseFloat(value.replace(/[^0-9.]/g, ""));
				if(!isNaN(value))
				{
					parsedValue = Math.floor(value * 100);
				}
			}
			catch(e)
			{
				
			}
		}
		return parsedValue;
	}
		
};





/**
 * A utility for formatting dates.
 * @namespace DateUtil
 */
var DateUtil = DateUtil ||
{
	
	/**
	 * Default format pattern "yyyy-MM-dd HH:mm:ss z".
	 * @memberof DateUtil
	 * @member {String}
	 */
	defaultPattern : "yyyy-MM-dd HH:mm:ss z",
	
	/**
	 * Default lang "en".
	 * @memberof DateUtil
	 * @member {String}
	 */
	defaultLang : "en",
	
	/**
	 * A regular expression compliant with java's date format.
	 * @memberof DateUtil
	 * @member {RegExp}
	 */
	formatRegExp : (function()
	{
		var builder = "";
		builder += "''|'|";
		builder += "yyyy|yy|";
		builder += "MMMM|MMM|MM|M|";
		builder += "dd|d|";
		builder += "EEEE|EEE|u|";
		builder += "HH|H|";
		builder += "kk|k|";
		builder += "KK|K|";
		builder += "hh|h|";
		builder += "mm|m|";
		builder += "ss|s|";
		builder += "SSS|SS|S|";
		builder += "a|";
		builder += "zzz|zz|z|";
		builder += "Z|X|";
		return new RegExp(builder, "g");
	})(),
	
	/**
	 * Language translations for month and day names.
	 * @memberof DateUtil
	 * @member {Object}
	 */
	label :
	{
		"en" :
		{
			"longMonths" : ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
			"shortMonths" : ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
			"longDays" : ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
			"shortDays" : ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
		},
		"es" :
		{
			"longMonths" : ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
			"shortMonths" : ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
			"longDays" : ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
			"shortDays" : ["Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"]
		}
	},
	
	/**
	 * Create a date using natural month value 1-12 instead of 0-11.
	 * @memberof DateUtil
	 * @method
	 * @param {Number} year
	 * @param {Number} month
	 * @param {Number} day
	 * @param {Number} [hour]
	 * @param {Number} [minutes]
	 * @param {Number} [seconds]
	 * @param {Number} [milliseconds]
	 * @returns {Date}
	 */
	get : function(year, month, day, hour, minutes, seconds, milliseconds)
	{
		month = !isNaN(month) ? month - 1 : 0;
		return new Date(year, month, day, hour, minutes, seconds, milliseconds);
	},
	
	/**
	 * Format a date or timestamp into a string pattern.
	 * @memberof DateUtil
	 * @method
	 * @param {String} [pattern]
	 * @param {Date|Number} [date]
	 * @param {String} [lang]
	 * @returns {String}
	 */
	format : function(pattern, date, lang)
	{
		var self = this;
		pattern = isValidString(pattern) ? pattern : this.defaultPattern;
		if(!isNaN(date))
		{
			date = new Date(date);
		}
		else if(!isDate(date))
		{
			date = new Date();
		}
		lang = isSet(this.label[lang]) ? lang : this.defaultLang;
		var escape = false;
		var formattedDate = pattern.replace(this.formatRegExp, function(match, capture)
		{
			switch(match)
			{
				case "''":
				{
					return "'";
				}
				case "'":
				{
					escape = !escape;
					return "";
				}
				default:
				{
					if(escape)
					{
						return match;
					}
				}
			}
			var replacement = "";
			switch(match)
			{
				case "yyyy":
				{
					replacement = new String(date.getFullYear());
					break;
				}
				case "yy":
				{
					replacement = new String(date.getFullYear()).slice(-2);
					break;
				}
				case "MMMM":
				{
					replacement = self.label[lang].longMonths[date.getMonth()];
					break;
				}
				case "MMM":
				{
					replacement = self.label[lang].shortMonths[date.getMonth()];
					break;
				}
				case "MM":
				case "M":
				{
					replacement = StringUtil.padNumber(date.getMonth() + 1, match.length);
					break;
				}
				case "dd":
				case "d":
				{
					replacement = StringUtil.padNumber(date.getDate(), match.length);
					break;
				}
				case "EEEE":
				{
					replacement = self.label[lang].longDays[date.getDay()];
					break;
				}
				case "EEE":
				{
					replacement = self.label[lang].shortDays[date.getDay()];
					break;
				}
				case "u":
				{
					replacement = new String(date.getDay() + 1);
					break;
				}
				case "HH":
				case "H":
				{
					replacement = StringUtil.padNumber(date.getHours(), match.length);
					break;
				}
				case "kk":
				case "k":
				{
					replacement = StringUtil.padNumber(date.getHours() + 1, match.length);
					break;
				}
				case "KK":
				case "K":
				{
					replacement = StringUtil.padNumber(date.getHours() % 12 - 1, match.length);
					break;
				}
				case "hh":
				case "h":
				{
					replacement = StringUtil.padNumber(date.getHours() % 12 || 12, match.length);
					break;
				}
				case "mm":
				case "m":
				{
					replacement = StringUtil.padNumber(date.getMinutes(), match.length);
					break;
				}
				case "ss":
				case "s":
				{
					replacement = StringUtil.padNumber(date.getSeconds(), match.length);
					break;
				}
				case "SSS":
				case "SS":
				case "S":
				{
					replacement = StringUtil.padNumber(date.getMilliseconds(), match.length);
					break;
				}
				case "a":
				{
					replacement = date.getHours() < 12 ? "AM" : "PM";
					break;
				}
				case "zzz":
				case "zz":
				case "z":
				{
					var matcher = /\((\w*)\)/.exec(date.toString());
					if(!isNull(matcher))
					{
						replacement = matcher[1];
					}
					break;
				}
				case "Z":
				case "X":
				{
					var offsetMinutes = date.getTimezoneOffset();
					var sign = offsetMinutes <= 0 ? "+" : "-";
					var offsetHours = Math.abs(offsetMinutes / 60);
					var hours = Math.floor(offsetHours);
					var minutes = Math.round((offsetHours - hours) * 60);
					replacement = sign + StringUtil.padNumber(hours, 2) + StringUtil.padNumber(minutes, 2);
					break;
				}
			}
			return replacement;
		});
		return formattedDate;
	},
	
	/**
	 * Creates a regular expression for parsing a date string
	 * @memberof DateUtil
	 * @method
	 * @param {String} [pattern]
	 * @param {String} [lang]
	 * @returns {Object}
	 */
	parser : function(pattern, lang)
	{
		var self = this;
		pattern = isValidString(pattern) ? pattern : this.defaultPattern;
		lang = isSet(this.label[lang]) ? lang : this.defaultLang;
		var groups = [];
		var escape = false;
		var builder = pattern.replace(this.formatRegExp, function(match, capture)
		{
			switch(match)
			{
				case "''":
				{
					return "'";
				}
				case "'":
				{
					escape = !escape;
					return "";
				}
				default:
				{
					if(escape)
					{
						return match;
					}
				}
			}
			var replacement = "";
			switch(match)
			{
				case "yyyy":
				{
					replacement = "([0-9]{4})";
					break;
				}
				case "yy":
				{
					replacement = "([0-9]{2})";
					break;
				}
				case "MMMM":
				{
					replacement = "(" + self.label[lang].longMonths.join("|") + ")";
					break;
				}
				case "MMM":
				{
					replacement = "(" + self.label[lang].shortMonths.join("|") + ")";
					break;
				}
				case "MM":
				{
					replacement = "([0][1-9]|[1][0-2])";
					break;
				}
				case "M":
				{
					replacement = "([0][1-9]|[1][0-2]|[1-9])";
					break;
				}
				case "dd":
				{
					replacement = "([0][1-9]|[1-2][0-9]|[3][0-1])";
					break;
				}
				case "d":
				{
					replacement = "([0][1-9]|[1-2][0-9]|[3][0-1]|[1-9])";
					break;
				}
				case "EEEE":
				{
					replacement = "(" + self.label[lang].longDays.join("|") + ")";
					break;
				}
				case "EEE":
				{
					replacement = "(" + self.label[lang].shortDays.join("|") + ")";
					break;
				}
				case "u":
				{
					replacement = "([1-7])";
					break;
				}
				case "HH":
				{
					replacement = "([0-1][0-9]|[2][0-3])";
					break;
				}
				case "H":
				{
					replacement = "([0-1][0-9]|[2][0-3]|[0-9])";
					break;
				}
				case "kk":
				{
					replacement = "([0][1-9]|[1][0-9]|[2][0-4])";
					break;
				}
				case "k":
				{
					replacement = "([0][1-9]|[1][0-9]|[2][0-4]|[0-9])";
					break;
				}
				case "KK":
				{
					replacement = "([0][0-9]|[1][0-1])";
					break;
				}
				case "K":
				{
					replacement = "([0][0-9]|[1][0-1]|[0-9])";
					break;
				}
				case "hh":
				{
					replacement = "([0][1-9]|[0-1][0-2])";
					break;
				}
				case "h":
				{
					replacement = "([0][1-9]|[0-1][0-2]|[1-9])";
					break;
				}
				case "mm":
				{
					replacement = "([0-5][0-9])";
					break;
				}
				case "m":
				{
					replacement = "([0-5][0-9]|[0-9])";
					break;
				}
				case "ss":
				{
					replacement = "([0-5][0-9])";
					break;
				}
				case "s":
				{
					replacement = "([0-5][0-9]|[0-9])";
					break;
				}
				case "SSS":
				{
					replacement = "([0-9]{3})";
					break;
				}
				case "SS":
				{
					replacement = "([0-9]{2,3})";
					break;
				}
				case "S":
				{
					replacement = "([0-9]{1,3})";
					break;
				}
				case "a":
				{
					replacement = "(AM|PM)";
					break;
				}
				default:
				{
					match = null;
					break;
				}
			}
			if(match)
			{
				groups.push(match)
			}
			return replacement;
		});
		return {
			regExp : new RegExp(builder, "i"),
			groups : groups
		}
	},
	
	/**
	 * Uses a parser to validate date string against a pattern
	 * @memberof DateUtil
	 * @method
	 * @param {String} pattern
	 * @param {String} value
	 * @param {String} [lang]
	 * @returns {Boolean}
	 */
	isValid : function(pattern, value, lang)
	{
		var parser = this.parser(pattern, lang);
		return parser.regExp.test(value);
	},
	
	/**
	 * Parses a date string into a date object
	 * @memberof DateUtil
	 * @method
	 * @param {String} [pattern]
	 * @param {String} [value]
	 * @param {String} [lang]
	 * @returns {Date}
	 */
	parse : function(pattern, value, lang)
	{
		var self = this;
		lang = isSet(this.label[lang]) ? lang : this.defaultLang;
		var parser = this.parser(pattern, lang);
		var date = null;
		var matcher = parser.regExp.exec(value);
		if(matcher)
		{
			var defaultDate = new Date();
			var year = defaultDate.getYear();
			var month = defaultDate.getMonth();
			var day = 1;
			var hours = 0;
			var minutes = 0;
			var seconds = 0;
			var milliseconds = 0;
			var pm = false;
			for(var i in parser.groups)
			{
				var group = parser.groups[i];
				var capture = matcher[new Number(i) + 1];
				switch(group)
				{
					case "yyyy":
					{
						year = new Number(capture);
						break;
					}
					case "yy":
					{
						year = new Number(capture) + 2000;
						break;
					}
					case "MMMM":
					{
						capture = capture.charAt(0).toUpperCase() + capture.slice(1).toLowerCase();
						var index = self.label[lang].longMonths.indexOf(capture);
						if(index > -1)
						{
							month = index;
						}
						break;
					}
					case "MMM":
					{
						capture = capture.charAt(0).toUpperCase() + capture.slice(1).toLowerCase();
						var index = self.label[lang].shortMonths.indexOf(capture);
						if(index > -1)
						{
							month = index;
						}
						break;
					}
					case "MM":
					case "M":
					{
						month = new Number(capture) - 1;
						break;
					}
					case "dd":
					case "d":
					{
						day = new Number(capture);
						break;
					}
					case "EEEE":
					case "EEE":
					case "u":
					{
						// ignore weekday
						break;
					}
					case "HH":
					case "H":
					{
						hours = new Number(capture);
						break;
					}
					case "kk":
					case "k":
					{
						hours = new Number(capture) - 1;
						break;
					}
					case "KK":
					case "K":
					{
						hours = new Number(capture) + 1;
						break;
					}
					case "hh":
					case "h":
					{
						hours = new Number(capture);
						break;
					}
					case "mm":
					case "m":
					{
						minutes = new Number(capture);
						break;
					}
					case "ss":
					case "s":
					{
						seconds = new Number(capture);
						break;
					}
					case "SSS":
					case "SS":
					case "S":
					{
						milliseconds = new Number(capture);
						break;
					}
					case "a":
					{
						pm = "PM" == capture.toUpperCase();
						break;
					}
				}
			}
			if(pm)
			{
				hours += 12;
				if(hours == 24)
				{
					hours = 0;
				}
			}
			date = new Date(year, month, day, hours, minutes, seconds, milliseconds);
		}
		return date;
	},
	
	/**
	 * Parses a date string into a date object
	 * @memberof DateUtil
	 * @method
	 * @param {String} parsePattern
	 * @param {String} formatPattern
	 * @param {String} value
	 * @param {String} [lang]
	 * @returns {Date}
	 */
	reformat : function(parsePattern, formatPattern, value, lang)
	{
		var date = parse(parsePattern, value, lang);
		if(isSet(date))
		{
			value = format(formatPattern, date, lang);
		}
		return value;
	}
	
};





/**
 * A utility for random ids.
 * @namespace IdUtil
 */
var IdUtil = IdUtil ||
{
	
	/**
	 * Default id length of 10.
	 * @memberof IdUtil
	 * @member {Number}
	 */
	defaultLength : 10,
	
	/**
	 * Default key for 0-9 a-z A-Z.
	 * @memberof IdUtil
	 * @member {String}
	 */
	defaultKey : "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
	
	/**
	 * Generates a random id.
	 * @memberof IdUtil
	 * @method
	 * @param {Number} [length]
	 * @param {String} [key]
	 * @returns {String}
	 */
	generate : function(length, key)
	{
		length = isNumber(length) ? length : this.defaultLength;
		key = isValidString(key) ? key : this.defaultKey;
		var value = "";
		for(var i = 0; i < length; i++)
		{
			value += key.charAt(Math.floor(Math.random() * key.length));
		}
		return value;
	}
	
};





/**
 * A utility for formatting numbers.
 * @namespace NumberUtil
 */
var NumberUtil = NumberUtil ||
{
	
	/**
	 * Format a string or number as an int.
	 * @memberof NumberUtil
	 * @method
	 * @param {Number|String} value
	 * @returns {String}
	 */
	formatInt : function(value)
	{
		var parsedValue = parseInt(value);
		return !isNaN(parsedValue) ? parsedValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") : "";
	},
	
	/**
	 * Format a string or number as a decimal of 2 places.
	 * @memberof NumberUtil
	 * @method
	 * @param {Number|String} value
	 * @returns {String}
	 */
	formatDecimal : function(value)
	{
		var parsedValue = parseFloat(value);
		return !isNaN(parsedValue) ? parsedValue.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",") : "";
	},
	
	/**
	 * Format a string or number as a percentage.
	 * @memberof NumberUtil
	 * @method
	 * @param {Number|String} value
	 * @param {Number} [decimal]
	 * @returns {String}
	 */
	formatPercent : function(value, decimal)
	{
		decimal = isNumber(decimal) ? decimal : 0;
		var parsedValue = parseFloat(value);
		return !isNaN(parsedValue) ? (parsedValue * 100).toFixed(decimal) : "";
	}
	
};





/**
 * A utility for manipulating objects.
 * @namespace ObjectUtil
 */
var ObjectUtil = ObjectUtil ||
{
	
	/**
	 * Eval a string into an object.
	 * @memberof ObjectUtil
	 * @method
	 * @param {*} value
	 * @returns {Object}
	 */
	parse : function(value)
	{
		var object = null;
		try
		{
			if(isObject(value))
			{
				object = value;
			}
			else
			{
				eval("object = " + value);
			}
		}
		catch(e)
		{
			
		}
		return isObject(object) ? object : {};
	},
	
	/**
	 * Retrieve an element from an object using a dot notation path.
	 * @memberof ObjectUtil
	 * @method
	 * @param {Object} object
	 * @param {path} path
	 * @returns {*}
	 */
	find : function(object, path)
	{
		var paths = path.split(".");
		for(var i in paths)
		{
			if(isSet(object[paths[i]]))
			{
				object = object[paths[i]];
			}
			else
			{
				object = null;
				break;
			}
		}
		return object;
	}
	
};




/**
 * A utility for manipulating strings.
 * @namespace StringUtil
 */
var StringUtil = StringUtil ||
{
	
	/**
	 * Zero left pad a number.
	 * @memberof StringUtil
	 * @method
	 * @param {Number|String} value
	 * @param {Number} length
	 * @returns {String}
	 */
	padNumber : function(value, length)
	{
		return this.pad(new String(value), length, "0", true);
	},
	
	/**
	 * Left pad a string with character.
	 * @memberof StringUtil
	 * @method
	 * @param {String} value
	 * @param {Number} length
	 * @param {String} character
	 * @returns {String}
	 */
	padLeft : function(value, length, character)
	{
		return this.pad(value, length, character, true);
	},
	
	/**
	 * Right pad a string with character.
	 * @memberof StringUtil
	 * @method
	 * @param {Number|String} value
	 * @param {Number} length
	 * @param {String} character
	 * @returns {String}
	 */
	padRight : function(value, length, character)
	{
		return this.pad(value, length, character, false);
	},
	
	/**
	 * Pad a string in direction of left boolean.
	 * @memberof StringUtil
	 * @method
	 * @param {String} value
	 * @param {Number} length
	 * @param {String} character
	 * @param {Boolean} left
	 * @returns {String}
	 */
	pad : function(value, length, character, left)
	{
		if(value.length < length)
		{
			character = isValidString(character) ? character.substring(0, 1) : " ";
			var pad = new Array(length + 1 - value.length).join(character);
			return left ? pad + value : value + pad;
		}
		else
		{
			return value;
		}
	},
	
	/**
	 * Repeat a value for specified length.
	 * @memberof StringUtil
	 * @method
	 * @param {String} value
	 * @param {Number} length
	 * @returns {String}
	 */
	repeat : function(value, length)
	{
		var repeated = "";
		for(var i = 0; i < length; i++)
		{
			repeated += value;
		}
		return repeated;
	},
	
	/**
	 * Compare two strings.
	 * @memberof StringUtil
	 * @method
	 * @param {String} value1
	 * @param {String} value2
	 * @param {Boolean} [caseInsensitive]
	 * @returns {String}
	 */
	equals : function(value1, value2, caseInsensitive)
	{
		caseInsensitive = isSet(caseInsensitive) ? caseInsensitive : true;
		var equals = false;
		if(value1 && value2)
		{
			equals = new String(value1).match(new RegExp("^" + new String(value2) + "$", caseInsensitive ? "i" : "")) !== null;
		}
		return equals;
	},
	
	/**
	 * Uppercase the first character.
	 * @memberof StringUtil
	 * @method
	 * @param {String} value
	 * @returns {String}
	 */
	capitalize : function(value)
	{
		return value.charAt(0).toUpperCase() + value.slice(1);
	},
	
	/**
	 * Replaces named parameters with values from param object
	 * @method
	 * @param {String} value
	 * @param {Object} param
	 * @returns {String}
	 */
	replace : function(value, param)
	{
		value = value.replace(/{{\s*?(\w+)\s*?}}/g, function(match, capture)
		{
			var replacement = ObjectUtil.find(param, capture);
			return isSet(replacement) ? replacement : "";
		});
		return value;
	}
	
};



