var roth = roth || {};
roth.js = roth.js || {};
roth.js.env = roth.js.env || {};

roth.js.env.hosts = {};
roth.js.env.dependencies = [];

var getDependencies = function()
{
	return JSON.stringify(roth.js.env.dependencies);
};

var loadDependencies = function(){};
