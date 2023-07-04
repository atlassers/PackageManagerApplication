package it.euris.gd.vegans.packagingmanager.exceptions;

public class AllItemsExceedTheMaxPriceException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private static final String BASE = "All items exceed the max price";
  private static final String EXTENDED = BASE + " [the maximum is %f €].";


  public AllItemsExceedTheMaxPriceException() {
    super(BASE);
  }

  public AllItemsExceedTheMaxPriceException(Double limit) {
    super(String.format(EXTENDED, limit));
  }
}
