package interviewcake.products_of_all_ints_except_at_index;

/**
 * Created by mullermw on 8/12/15.
 */
public class ProductOfAllIntsExceptAtIndex {

    public int[] get_products_of_all_ints_except_at_index(int[] arr) {

        if (arr.length < 2) throw new IllegalArgumentException();

        int[] products = new int[arr.length];

        int product = 1;
        for (int i=1; i<arr.length; ++i) {
            products[i] = product;
            product *= arr[i];
        }

        product = 1;
        for (int i=arr.length-1; i>0; --i) {
            products[i] *= product;
            product *= arr[i];
        }

        return products;

    }


}
