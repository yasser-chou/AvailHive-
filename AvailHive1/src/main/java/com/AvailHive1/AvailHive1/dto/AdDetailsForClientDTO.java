package com.AvailHive1.AvailHive1.dto;


import com.AvailHive1.AvailHive1.dto.AdDTO;
import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDTO {

    private AdDTO adDTO;

    private List<ReviewDTO> reviewDTOList;
}
