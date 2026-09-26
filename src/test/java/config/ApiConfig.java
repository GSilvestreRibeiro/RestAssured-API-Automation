package config;

public final class ApiConfig {
  private ApiConfig() {
  }

  public static final String BASE_URL = System.getProperty("baseUrl", "https://serverest.dev");
  public static final String CONTENT_TYPE = "application/json";
}
