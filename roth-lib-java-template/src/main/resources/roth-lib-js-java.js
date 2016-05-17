

var forEachList = forEachList || function(list, callback)
{
	for(var i = 0; i < list.size(); i++)
	{
		var loop =
		{
			index 	: i,
			length 	: list.size(),
			first	: i == 0,
			last	: i == list.size() - 1
		};
		if(isFalse(callback(list.get(i), i, loop)))
		{
			break;
		}
	}
};


var forEachMap = forEachMap || function(map, callback)
{
	for(var i = 0; i < map.size(); i++)
	{
		var loop =
		{
			index 	: i,
			length 	: map.size(),
			first	: i == 0,
			last	: i == map.size() - 1
		};
		var key = map.key(i);
		if(isFalse(callback(map.get(key), key, loop)))
		{
			break;
		}
	}
};

