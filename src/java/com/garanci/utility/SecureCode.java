/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.garanci.utility;

import java.math.BigInteger;
import java.security.SecureRandom;
/**
 *
 * getPassword method will generate secure alphanumeric code.
 * It is used in generating activation code in sign up page and in password recovery.
 */
public class SecureCode 
{
    private SecureRandom random = new SecureRandom();
    public String getPassword()
    {
        return new BigInteger(50, random).toString(32);
    }
}