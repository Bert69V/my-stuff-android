package de.telekom.sea.mystuff.frontend.android.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class Item {

    private Long id;

    private String name;
    private int amount;
    private String location;
    private String description;
    private LocalDate lastUsed;


}
