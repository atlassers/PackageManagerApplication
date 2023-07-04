package it.euris.gd.vegans.packagingmanager.exceptions;


public class MaxWeightAdmittedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private static final String BASE = "The weight exceeded the limit.";
  private static final String EXTENDED =
      BASE + "The weight exceeded the limit [%f Kg when the maximum is %f} Kg].";


  public MaxWeightAdmittedException() {
    super();
  }

  public MaxWeightAdmittedException(Double limit, Double currentWeight) {
    super(String.format(EXTENDED, currentWeight, limit));
  }
}
