package it.euris.gd.vegans.packagingmanager.exceptions;


public class MaxPriceAdmittedException extends RuntimeException {

  private static final String BASE = "The price exceeded the limit";
  private static final String EXTENDED =
      BASE + " [%f € when the maximum is %f €].";

  private static final long serialVersionUID = 1L;


  public MaxPriceAdmittedException() {
    super(BASE);
  }

  public MaxPriceAdmittedException(Double limit, Double currentPrice) {
    super(String.format(EXTENDED, currentPrice, limit));
  }

}
