var roth = roth || {};
roth.js = roth.js || {};
roth.js.template = roth.js.template || {};
roth.js.template.version = "0.1.1-SNAPSHOT";


roth.js.template.Template = function(config)
{
	var self = this;
	
	var Config =
	{
		openUnescapedExpression 	: "{{{",
		openEscapedExpression 		: "{{",
		openStatement 				: "{%",
		closeUnescapedExpression	: "}}}",
		closeEscapedExpression		: "}}",
		closeStatement				: "%}",
		dataVar						: "$_d",
		escapeVar					: "$_e",
		issetVar					: "$_i",
		argVar						: "$_a",
		tempVar						: "$_t",
		sourceVar					: "$_s"
	};
	
	var escapeRegExp = function(value)
	{
		return value.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
	};
	
	var syntaxRegExp = (function()
	{
		config = typeof config === "object" ? config : {};
		for(var name in Config)
		{
			if(config[name] === undefined || config[name] === null || config[name] == "")
			{
				config[name] = Config[name];
			}
		}
		var regExpBuilder = "";
		regExpBuilder += "\\n|";
		regExpBuilder += "\\\"|";
		regExpBuilder += escapeRegExp(config.openUnescapedExpression) + "|";
		regExpBuilder += escapeRegExp(config.openEscapedExpression) + "|";
		regExpBuilder += escapeRegExp(config.openStatement) + "|";
		regExpBuilder += escapeRegExp(config.closeUnescapedExpression) + "|";
		regExpBuilder += escapeRegExp(config.closeEscapedExpression) + "|";
		regExpBuilder += escapeRegExp(config.closeStatement) + "|";
		regExpBuilder += "defined\\((.+?)\\)";
		return new RegExp(regExpBuilder, "g");
	})();
	
	this.parse = function(source, data)
	{
		var escape = true;
		var parsedSource = "";
		if(typeof data === "object")
		{
			for(var name in data)
			{
				parsedSource += "var " + name + " = " + config.dataVar + "[\"" + name + "\"];\n";
			}
		}
		parsedSource += "var " + config.escapeVar + " = function(" + config.argVar + ") { return " + config.argVar + ".replace(/&/g, \"&amp;\").replace(/</g, \"&lt;\").replace(/>/g, \"&gt;\"); };\n";
		parsedSource += "var " + config.issetVar + " = function(" + config.argVar + ") { return "  + config.argVar + " !== undefined && " + config.argVar + " !== null };\n";
		parsedSource += "var " + config.tempVar + ";\nvar " + config.sourceVar + "=\"\";\n" + config.sourceVar + "+=\"";
		parsedSource += source.replace(syntaxRegExp, function(match, capture)
		{
			var replacement = "";
			switch(match)
			{
				case "\n":
				{
					replacement = escape ? "\\n" : "\n";
					break;
				}
				case "\"":
				{
					replacement = escape ? "\\\"" : "\"";
					break;
				}
				case config.openUnescapedExpression:
				{
					replacement = "\"; try{" + config.tempVar + "=";
					escape = false;
					break;
				}
				case config.openEscapedExpression:
				{
					replacement = "\"; try{" + config.tempVar + "=";
					escape = false;
					break;
				}
				case config.openStatement:
				{
					replacement = "\";";
					escape = false;
					break;
				}
				case config.closeUnescapedExpression:
				{
					replacement = "; " + config.sourceVar + "+=(" + config.issetVar + "(" + config.tempVar + ")) ? new String(" + config.tempVar + ") : \"\";}catch(e){}; " + config.sourceVar + "+=\"";
					escape = true;
					break;
				}
				case config.closeEscapedExpression:
				{
					replacement = "; " + config.sourceVar + "+=(" + config.issetVar + "(" + config.tempVar + ")) ? " + config.escapeVar + "(new String(" + config.tempVar + ")) : \"\";}catch(e){}; " + config.sourceVar + "+=\"";
					escape = true;
					break;
				}
				case config.closeStatement:
				{
					replacement = config.sourceVar + "+=\"";
					escape = true;
					break;
				}
				default:
				{
					if(match.indexOf("defined") == 0)
					{
						replacement = "typeof " + capture + " !== " + "\"undefined\"";
					}
					break;
				}
			}
			return replacement;
		});
		parsedSource += "\";\nreturn " + config.sourceVar + ";";
		return parsedSource;
	};
	
	this.renderParsed = function(parsedSource, data)
	{
		return new Function(config.dataVar, parsedSource)(data);
	};
	
	this.render = function(source, data)
	{
		return this.renderParsed(this.parse(source, data), data);
	};
	
}
