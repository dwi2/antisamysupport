package play.modules.antisamysupport;

import play.*;
import play.classloading.*;

public class AntiSamyPlugin extends PlayPlugin {

  public final static String EMPTY = "";
  public final static String FILTERED = "content filtered";

  private static boolean pluginActive = false;
  private static AntiSamyService service = null;

  public void onApplicationStart() {
    Logger.info("Start to initialize AntiSamySupport plugin...");
    try {
      service = (AntiSamyService) new AntiSamyServiceImpl();
      pluginActive = true;
      Logger.info("AntiSamySupport successfully initialized!");
    } catch (Exception e) {
      Logger.error(e, "AntiSamySupport plugin disabled. Error when creating new instance");
    }
  }

  public static String filter(String input) {
    if (pluginActive == true) {
      return service.filter(input);
    }
    else {
      return FILTERED;
    }
  }
}
