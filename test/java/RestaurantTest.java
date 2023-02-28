import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
@BeforeEach
public void refactor_restaurant_test()
{
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    restaurant.addToMenu("Sweet corn soup",119);
    restaurant.addToMenu("Vegetable lasagne", 269);
    restaurant.addToMenu("Corn cheese balls",319);

}
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant r= Mockito.spy(restaurant);
        LocalTime open_time = LocalTime.parse("16:30:00");
        Mockito.when(r.getCurrentTime()).thenReturn(open_time);
        assertTrue(r.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant r = Mockito.spy(restaurant);
        LocalTime open_time = LocalTime.parse("23:30:00");
        Mockito.when(r.getCurrentTime()).thenReturn(open_time);
        assertFalse(r.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
      int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
            assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>
@Test
    public void calculate_order_total_of_all_the_selected_items()
    {
        List<String> items_list= new ArrayList<String>();
        items_list.add("Sweet corn soup");
        items_list.add("Vegetable lasagne");
        assertEquals(388,restaurant.order_total(items_list));
    }
    //<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void displaying_details_of_restaurant_to_verify_it_contains_same_as_added_to_the_obj()
    {
        restaurant.displayDetails();
    }
}