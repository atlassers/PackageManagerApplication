package it.euris.gd.vegans.packagingmanager.exceptions;


public class AllItemsExceedTheMaxWeightException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private static final String BASE = "All items exceed the max weight";
  private static final String EXTENDED = BASE + " [the maximum is %f Kg].";


  public AllItemsExceedTheMaxWeightException() {
    super(BASE);
  }

  public AllItemsExceedTheMaxWeightException(Double limit) {
    super(String.format(EXTENDED, limit));
  }
}
