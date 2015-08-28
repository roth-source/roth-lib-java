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
	}
	
	var escapeRegexp = function(value)
	{
		return value.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
	};
	
	var syntaxRegexp = (function()
	{
		config = config || {};
		for(var name in Config)
		{
			if(config[name] === undefined || config[name] === null || config[name] == "")
			{
				config[name] = Config[name];
			}
		}
		var regexpBuilder = "";
		regexpBuilder += "\n|";
		regexpBuilder += "\"|";
		regexpBuilder += escapeRegexp(config.openUnescapedExpression) + "|";
		regexpBuilder += escapeRegexp(config.openEscapedExpression) + "|";
		regexpBuilder += escapeRegexp(config.openStatement) + "|";
		regexpBuilder += escapeRegexp(config.closeUnescapedExpression) + "|";
		regexpBuilder += escapeRegexp(config.closeEscapedExpression) + "|";
		regexpBuilder += escapeRegexp(config.closeStatement);
		return new RegExp(regexpBuilder, "g");
	})();
	
	this.render = function(source, data)
	{
		var literal = true;
		var parsedSource = "";
		if(typeof data === "object")
		{
			for(var name in data)
			{
				parsedSource += "var " + name + " = " + config.dataVar + "[\"" + name + "\"];\n";
			}
		}
		parsedSource += "var " + config.escapeVar + " = function(" + config.argVar + ") { return " + config.argVar + ".replace(/&/g, \"&amp;\").replace(/</g, \"&lt;\").replace(/>/g, \"&gt;\"); };\n";
		parsedSource += "var " + config.issetVar + " = function(" + config.argVar + ") { return " + config.argVar + " !== undefined && " + config.argVar + " !== null };\n";
		parsedSource += "var " + config.tempVar + ";\nvar " + config.sourceVar + "=\"\";\n" + config.sourceVar + "+=\"";
		parsedSource += source.replace(syntaxRegexp, function(match, offest)
		{
			var replacement = "";
			switch(match)
			{
				case "\n":
				{
					replacement = literal ? "\\n" : "\n";
					break;
				}
				case "\"":
				{
					replacement = literal ? "\\\"" : "\"";
					break;
				}
				case config.openUnescapedExpression:
				{
					replacement = "\"; try{" + config.tempVar + "=";
					literal = false;
					break;
				}
				case config.openEscapedExpression:
				{
					replacement = "\"; try{" + config.tempVar + "=";
					literal = false;
					break;
				}
				case config.openStatement:
				{
					replacement = "\";";
					literal = false;
					break;
				}
				case config.closeUnescapedExpression:
				{
					replacement = "; " + config.sourceVar + "+=(" + config.issetVar + "(" + config.tempVar + ")) ? new String(" + config.tempVar + ") : \"\";}catch(e){}; " + config.sourceVar + "+=\"";
					literal = true;
					break;
				}
				case config.closeEscapedExpression:
				{
					replacement = "; " + config.sourceVar + "+=(" + config.issetVar + "(" + config.tempVar + ")) ? " + config.escapeVar + "(new String(" + config.tempVar + ")) : \"\";}catch(e){}; " + config.sourceVar + "+=\"";
					literal = true;
					break;
				}
				case config.closeStatement:
				{
					replacement = "" + config.sourceVar + "+=\"";
					literal = true;
					break;
				}
			}
			return replacement;
		});
		parsedSource += "\";\nreturn " + config.sourceVar + ";";
		var renderSource = new Function(config.dataVar, parsedSource);
		return renderSource(data);
	}
	
}