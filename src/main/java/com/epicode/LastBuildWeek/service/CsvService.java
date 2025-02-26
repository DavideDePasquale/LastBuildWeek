package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.model.Comune;
import com.epicode.LastBuildWeek.model.Province;
import com.epicode.LastBuildWeek.repository.ComuneRepository;
import com.epicode.LastBuildWeek.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    @Autowired ProvinceRepository provinceRepository;
    @Autowired ComuneRepository comuneRepository;


    public void importProvinces(MultipartFile file) throws IOException {
        List<Province> provinces = new ArrayList<>();
        //legge il file e dopo fa la conversione.
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        String line;
        boolean firstLine = true;
        while ((line= br.readLine()) != null){
           if (firstLine){
               firstLine = false;
               continue;
           }
           String [] data = line.split(";");
           if (data.length >= 3 ){
               Province province = new Province(data[0],data[1],data[2],new ArrayList<>());
               provinces.add(province);

           }

       }

        provinceRepository.saveAll(provinces);
    }
    public void importComuni(MultipartFile file) throws IOException{
        List<Comune> comuni = new ArrayList<>();
        //legge il file e dopo converte.
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),StandardCharsets.UTF_8));
        String line;
        boolean firstLine = true;
        while ((line = br.readLine()) != null){
            if (firstLine){
                firstLine = false;
                continue;
            }
            String [] data = line.split(";");
            if (data.length >= 3){
                String provinceCode = data[0];
                Province province = provinceRepository.findById(Long.valueOf(provinceCode)).orElseThrow(()-> new RuntimeException("Codice non trovato"));
                Comune comune = new Comune();
                comune.setNome(data[2]);
                comune.setProvince(province);
                comuni.add(comune);
            }

        }
        comuneRepository.saveAll(comuni);

    }
}
