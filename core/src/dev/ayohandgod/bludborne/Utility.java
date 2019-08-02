package dev.ayohandgod.bludborne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/** The type Utility. */
public class Utility {

  /** The constant _assetManager. */
  public static final AssetManager _assetManager = new AssetManager();

  private static final String TAG = Utility.class.getSimpleName();

  private static InternalFileHandleResolver _filePathResolver = new InternalFileHandleResolver();

  /**
   * Unload asset.
   *
   * @param assetFilenamePath the asset filename path
   */
  public static void unloadAsset(String assetFilenamePath) {
    if (_assetManager.isLoaded(assetFilenamePath)) {
      _assetManager.unload(assetFilenamePath);
    } else {
      Gdx.app.debug(TAG, "Asset is not loaded; Nothing to unload: " + assetFilenamePath);
    }
  }

  /**
   * Load completed float.
   *
   * @return the float
   */
  public static float loadCompleted() {
    return _assetManager.getProgress();
  }

  /**
   * Number assets queued int.
   *
   * @return the int
   */
  public static int numberAssetsQueued() {
    return _assetManager.getQueuedAssets();
  }

  /**
   * Update asset loading boolean.
   *
   * @return the boolean
   */
  public static boolean updateAssetLoading() {
    return _assetManager.update();
  }

  /**
   * Determine if asset loaded boolean.
   *
   * @param filename the filename
   * @return the boolean
   */
  public static boolean isAssetLoaded(String filename) {
    return _assetManager.isLoaded(filename);
  }

  public static void loadMapAsset(String mapFilenamePath) {
      if ( mapFilenamePath == null || mapFilenamePath.isEmpty() ) {
          return;
      }

      // load asset
      if(_filePathResolver.resolve(mapFilenamePath).exists()) {
          _assetManager.setLoader(
                  TiledMap.class, new TmxMapLoader(_filePathResolver)
          );

          _assetManager.load(mapFilenamePath, TiledMap.class);

          _assetManager.finishLoadingAsset(mapFilenamePath);
          Gdx.app.debug(TAG, "Map loaded!: " + mapFilenamePath);
      } else {
          Gdx.app.debug(TAG, "Map doesn't exist!: " + mapFilenamePath);
      }
  }

  public static TiledMap getMapAsset(String mapFilenamePath) {
      TiledMap map = null;

      if(_assetManager.isLoaded(mapFilenamePath)) {
          map = _assetManager.get(mapFilenamePath, TiledMap.class);
      } else {
          Gdx.app.log(TAG, "Map is not loaded: " + mapFilenamePath);
      }
      return map;
  }

  public static void loadTextureAsset(String textureFilenamePath) {
      if( textureFilenamePath ==  null || textureFilenamePath.isEmpty()) {
          return;
      }

      if(_filePathResolver.resolve(textureFilenamePath).exists()) {
          _assetManager.setLoader(Texture.class, new TextureLoader(_filePathResolver));

          _assetManager.load(textureFilenamePath, Texture.class);

          _assetManager.finishLoadingAsset(textureFilenamePath);
      } else {
          Gdx.app.log(TAG, "Texture doesn't exist: " + textureFilenamePath);
      }
  }

  public static Texture getTextureAsset(String textureFilenamePath) {
      Texture texture = null;

      if(_assetManager.isLoaded(textureFilenamePath)) {
          texture = _assetManager.get(textureFilenamePath, Texture.class);
      } else {
          Gdx.app.log(TAG, "Texture is not loaded: " + textureFilenamePath);
      }
      return texture;
  }
}
