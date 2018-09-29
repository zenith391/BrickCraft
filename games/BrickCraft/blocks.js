bc.blocks = {};

/*
bc.blocks.register("trollmod", "ghost_block", myGhostBlock);
var soulBlock = bc.blocks.get("brickcraft", "soul_block");
soulBlock.hardness = 4.0f;
bc.blocks.change("brickcraft", "soul_block", soulBlock);
*/

/**
Register a new block from the specified mod.
**/
bc.blocks.register = function(mod, id, block) {
	let b = engine.allocateNewBlock(mod, id);
	b.texture = block.texture;
	b.render = block.render;
	b.update = block.update;
	b.hardness = block.hardness;
	b.randomTicks = block.randomTick != null;
	b.randomTick = block.randomTick;
	engine.registerBlock(b);
}