package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

@RunWith(JUnit4.class)
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;


    @Test
    public void given_findAllItems_when_thereAreItems_then_returnNoEmptyList(){

        doReturn(anyList()).when(itemRepository).findAll();
    }
}
