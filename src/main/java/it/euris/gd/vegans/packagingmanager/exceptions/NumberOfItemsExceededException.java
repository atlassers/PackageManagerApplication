package it.euris.gd.vegans.packagingmanager.exceptions;


public class NumberOfItemsExceededException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private static final String BASE = "The number of items exceeded the limit";
  private static final String EXTENDED = BASE + " [%d items when the maximum is %d].";

  public NumberOfItemsExceededException() {
    super(BASE);
  }

  public NumberOfItemsExceededException(Integer limit, Integer numberOfItems) {
    super(String.format(EXTENDED, numberOfItems, limit));
  }

}
