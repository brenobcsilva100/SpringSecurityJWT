export class AppConstants {

  public static get baseServidor(): string{
    return "http://localhost:8085/"
  }

  public static get baseLogin(): string{
    return this.baseServidor + "login"
  }

  public static get baseUrl(): string{
    return this.baseServidor + "v1/professor"
  }

}
