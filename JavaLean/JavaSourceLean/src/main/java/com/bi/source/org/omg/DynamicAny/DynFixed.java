package org.omg.DynamicAny;


/**
* org/omg/DynamicAny/DynFixed.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /Users/ec2-user/workspace/Corretto8/macos/x64/build/Corretto8Src/installers/mac/tar/corretto-build/buildRoot/corba/src/share/classes/org/omg/DynamicAny/DynamicAny.idl
* Thursday, July 21, 2022 7:27:58 PM GMT
*/


/**
    * DynFixed objects support the manipulation of IDL fixed values.
    * Because IDL does not have a generic type that can represent fixed types with arbitrary
    * number of digits and arbitrary scale, the operations use the IDL string type.
    */
public interface DynFixed extends DynFixedOperations, org.omg.DynamicAny.DynAny, org.omg.CORBA.portable.IDLEntity 
{
} // interface DynFixed