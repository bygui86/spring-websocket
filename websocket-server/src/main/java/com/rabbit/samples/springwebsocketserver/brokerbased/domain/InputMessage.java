package com.rabbit.samples.springwebsocketserver.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
public class InputMessage {

	String from;

	String content;

}
