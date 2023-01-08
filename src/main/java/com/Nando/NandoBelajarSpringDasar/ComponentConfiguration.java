/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nando.NandoBelajarSpringDasar;

import data.MultiFoo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Acer
 */
@Configuration
@ComponentScan(basePackages = {
    "com.Nando.NandoBelajarspringDasar.repository",
    "com.Nando.NandoBelajarspringDasar.service",
    "com.Nando.NandoBelajarspringDasar.configuration",
})
@Import(MultiFoo.class)
public class ComponentConfiguration {
    
}
