package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.repository.ItemRepository;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(JUnit4.class)
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;
}
