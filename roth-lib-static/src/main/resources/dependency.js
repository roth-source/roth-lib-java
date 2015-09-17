
var hosts = {};
var dependencyMap = {};

var getStyleMap = function()
{
	if(typeof dependencyMap.styleMap !== "undefined")
	{
		return JSON.stringify(dependencyMap.styleMap);
	}
	return "{}";
};

var getAssetMap = function()
{
	if(typeof dependencyMap.assetMap !== "undefined")
	{
		return JSON.stringify(dependencyMap.assetMap);
	}
	return "{}";
}

var getScriptMap = function()
{
	if(typeof dependencyMap.scriptMap !== "undefined")
	{
		return JSON.stringify(dependencyMap.scriptMap);
	}
	return "{}";
};
