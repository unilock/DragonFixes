# DragonFixes

Various fixes for issues found in some of Reika's mods.
[GTNHLib](https://github.com/GTNewHorizons/GTNHLib) and [UniMixins](https://github.com/LegacyModdingMC/UniMixins) required.

Most credit goes to [gamrguy](https://github.com/gamrguy) a.k.a. "RobotLucca" a.k.a. "thegamemaster1234" for creating the original ASM-based ChromatiFixes

Fixes:
- CaveControl
  - Prevents `CaveLoader#getEffectiveBiome` from ever returning a null biome, fixing crashes usually caused by pylon chunk loading (thegamemaster1234)
- ChromatiCraft
  - With `disableArtefactShader` enabled, disables the Artefact shader (unilock)
  - Prevents crashes in `ChromaClientEventController#updateGlowCliffRendering` (thegamemaster1234)
  - Fixes the loadTransferRects method in a few NEI handlers, making them able to load with GTNH!NEI (FlamingKetchup)
  - Adds support for Xaero's maps where appropriate (unilock)
  - Caches the world directory path used during dungeon generation to make it much faster (FlamingKetchup)
  - Ignores the order progress was made in `ProgressionManager#isProgressionEqual` (unilock)
  - Prevents random ConcurrentModificationExceptions during pylon overlay rendering (thegamemaster1234)
  - Prevents the Lumen Beacon from interfering with itself (thegamemaster1234)
  - Enable Lumen Alveary Humidity and Temperature controls when the Princess is unable to work (as these are designed to allow the Princess to work in the first place) (thegamemaster1234)
  - Allows Cave Crystals and Potion Crystals to work with the Player Imitation Core, even if the player isn't in the immediate vicinity (unilock)
  - Prevents crashes caused by Adjacency Cores on neighbor state updates (thegamemaster1234, unilock)
- DragonAPI
  - Prevents NullPointerExceptions caused by `AbstractSearch$FoundPath#getPath`, preventing ChromatiCraft's Bezier Crystals from causing an Internal Server Error
  - With `disableClientSpecificConfigs` enabled, all "client-specific" configs use the same keys across all systems, allowing them to be packaged with modpacks and such (unilock)
- Satisforestry
  - Prevents a ClassCastException when removing a Slug item from slot 39 of any non-player inventory (unilock)
  - Allow Pressurizer multiblock to receive RotaryCraft power via Power Hub (thegamemaster1234)
- DragonRealmCore
  - With `disableTickInterceptASM` enabled, forcibly disables DragonRealmCore's "TickIntercept" class transformer - this prevents DRC's "PAUSEOFFLINE" config (disabled by default!) from functioning, but fixes compatibility with GTNHLib 0.6.19+ (unilock)
  - With `disableNetherShader` enabled, disables the Nether "heat" shader (unilock)
  - With `disableTerritoryStrongholdSystem` enabled, disables the TerritoryStrongholdSystem (unilock)
  - Prevents NullPointerExceptions caused by Hex Generators (thegamemaster1234)
  - With `energizationManagerFix` enabled, prevents crashes in `EnergizationManager#getEnergizationLevel` (however, the chosen fix prevents playing with the "Atmospheric Permeability Mediation" mechanic) (thegamemaster1234)
