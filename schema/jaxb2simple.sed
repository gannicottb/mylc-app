#!/bin/sed -f 
# jaxb2simple.sed September 2011 
# 
# Copyright (C) 2011, Dennis Bijwaard <bijwaard@...> 
# 
# Licensed under the Apache License, Version 2.0 (the "License"); 
# you may not use this file except in compliance with the License. 
# You may obtain a copy of the License at 
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software 
# distributed under the License is distributed on an "AS IS" BASIS, 
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
# implied. See the License for the specific language governing 
# permissions and limitations under the License. 

# note that simpleframework requires everything (elements/attributes) 
# by default, so required=false is often necessary 

# UPDATE PACKAGE NAME, ADJUST WHEN NEEDED 
s/^package generated;/package edu.lynchburg;/ 
# change jaxb imports to generic simpleframework one 
/import javax.xml.bind.annotation\./N 
/import javax.xml.bind.annotation\./N 
/import javax.xml.bind.annotation\./N 
/import javax.xml.bind.annotation\./N 
/import javax.xml.bind.annotation\./N 
s/^\(import javax.xml.bind.annotation.\w*;\n\)*import javax.xml.bind.annotation.\w*;/import org.simpleframework.xml.*;/ 
# change @XmlRootElement to @Root 
s/@XmlRootElement(\(.*\))/@Root(\1, strict=false)/ 
# change @XmlElement with list to @ElementList 
/@XmlElement/{ N 
  s/@XmlElement(name[      ]*=\(.*\)).*\(\n.*List\<.*\>.*;\)/@ElementList(inline=true, entry=\1)\2/ 
  s/@XmlElement(\(.*required.*\))/@Element(\1)/ 
  s/@XmlElement(\(.*\))/@Element(required=false,\1)/ 
} 
# change ordered items to @Order 
s/@XmlType(.*propOrder[    ]*\(=.*\)/@Order(elements\1/ 
# change @XmlAttribute to @Attribute 
s/@XmlAttribute(\(.*required\)/@Attribute(\1/ 
s/@XmlAttribute(/@Attribute(required=false,/ 
s/@XmlAttribute/@Attribute(required=false)/ 
# change XmlAccessorType+@XmlType to @Root, but with propOrder to @Order 
/@XmlAccessorType/{ N 
  s/@XmlAccessorType([ ]*XmlAccessType\.\(.*\))\n@XmlType(\(.*\))/@Default(DefaultType.\1)\n@Root(\2, strict=false)/ 
  s/@XmlAccessorType([ ]*XmlAccessType\.\(.*\))\n@XmlType(\(.*\)[ ]*,[ ]*propOrder[ ]*\(=.*\)/@Default(DefaultType.\1)\n@Order(elements\3/ 
} 
#s/@XmlAccessorType([ ]*XmlAccessType\.\(.*\))/@Default(DefaultType.\1)/ 
s/@XmlAccessorType([ ]*XmlAccessType\.\(.*\))/@Default(DefaultType.\1)/ 
s/@XmlType([ ]*name[ ]*=\(.*\))/@Root(name=\1, strict=false)/ 
/@XmlEnum/D 
/@XmlRegistry/D 
