var bc = {}

bc.logger = engine.newLogger("BrickCraft");

bc.load = function() {
	let mods = io.file("BrickCraft", "mods");
	if (!mods.exists()) {
		bc.logger.log("No mod folder!");
		mods.mkdirs();
	} else {
		let i = 0;
		let arr = mods.listFiles();
		while (i < arr.length) {
			bc.loadMod(arr[i]);
			i = i + 1;
		}
	}
	engine.exec("blocks.js");
}

bc.loadMod = function(mod) {
	
}

bc.logger.log("Hi! I'm muffin");

engine.setOnUnload(function() {
	bc.logger.log("Please i wanna die, die, die! Oh, thanks!");
});

engine.setOnLoad(function() {
	bc.logger.log("And it's muffin time!");
	bc.load();
});