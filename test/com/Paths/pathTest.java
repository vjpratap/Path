package com.Paths;

import org.junit.Test;

import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;

public class pathTest{
    @Test
    public void test_should_give_root_for_banglore_to_singapore() throws IOException {
        String[] args = {"Bangalore", "Singapore","-f", "file"};
        Root root = new Root(args);
        assertEquals("Bangalore->Singapore", root.givePath());
    }
    @Test
    public void test_should_give_root_for_singapore_to_banglore() throws IOException {
        String[] args = {"Singapore","Bangalore","-f", "file"};
        Root root = new Root(args);
        assertEquals("Singapore->Bangalore", root.givePath());
    }
    @Test
    public void test_should_give_root_for_banglore_to_tokyo() throws IOException {
        String[] args = {"Bangalore","Tokyo","-f", "file"};
        Root root = new Root(args);
        assertEquals("Bangalore->Singapore->Seoul->Beijing->Tokyo", root.givePath());
    }
    @Test
    public void test_should_give_root_for_tokyo_to_bangalore() throws IOException {
        String[] args = {"Tokyo","Bangalore","-f", "file"};
        Root root = new Root(args);
        assertEquals("Tokyo->Beijing->Seoul->Singapore->Bangalore", root.givePath());
    }
    @Test
    public void test_should_give_true_for_singapore_to_seoul() throws IOException {
        String[] args = {"Singapore","Seoul","-f", "file"};
        Root root = new Root(args);
        assertEquals("Singapore->Seoul", root.givePath());
    }
    @Test
    public void test_should_give_true_for_singapore_to_dubai() throws IOException {
        String[] args = {"Singapore","Dubai","-f", "file"};
        Root root = new Root(args);
        assertEquals("Singapore->Dubai", root.givePath());
    }
    @Test
    public void test_should_give_false_for_singapore_to_beijing() throws IOException {
        String[] args = {"Singapore","Beijing","-f", "file"};
        Root root = new Root(args);
        assertEquals("Singapore->Seoul->Beijing", root.givePath());
    }
    @Test
    public void test_should_give_noCity_for_singapore_to_lucknow() throws IOException {
        String[] args = {"Singapore","Lucknow","-f", "file"};
        Root root = new Root(args);
        assertEquals("no city Lucknow", root.givePath());
    }
    @Test
    public void test_should_give_noCity_for_Lucknow_to_Bangalore() throws IOException {
        String[] args = {"Lucknow","Singapore","-f", "file"};
        Root root = new Root(args);
        assertEquals("no city Lucknow", root.givePath());
    }
    @Test
    public void test_should_give_bad_file_name_when_the_file_is_not_exist() throws IOException {
        String[] args = {"Bangalore", "Singapore","-f", "fileeeee"};
        Root root = new Root(args);
        assertEquals("No database named fileeeee found.",root.givePath());
    }
    @Test
    public void test_should_give_country_name_with_the_city_when_we_give_flag_munus_c() throws IOException {
        String[] args = {"Bangalore", "Singapore","-f", "file","-c","cities.txt"};
        Root root = new Root(args);
        assertEquals("Bangalore[India]->Singapore[Singapore]",root.givePath());
    }
    @Test
    public void test_should_give_country_name_with_the_city_when_we_give_flag_munus_c_for_bangalore_to_tokyo() throws IOException {
        String[] args = {"Bangalore", "Tokyo","-f", "file","-c","cities.txt"};
        Root root = new Root(args);
        assertEquals("Bangalore[India]->Singapore[Singapore]->Seoul[South Korea]->Beijing[China]->Tokyo[Japan]",root.givePath());
    }
    @Test
    public void test_should_give_error_for_bad_fileName_of_country() throws IOException {
        String[] args = {"Bangalore", "Tokyo","-f", "file","-c","zynga.txt"};
        Root root = new Root(args);
        assertEquals("No database named zynga.txt found.",root.givePath());
    }
}