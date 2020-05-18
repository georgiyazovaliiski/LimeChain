pragma solidity ^0.4.17;

contract Limechain{
    address private creator;
    address public childOne;
    address public childTwo;
    uint public etherSpent;
    
    function Limechain(address chiOne, address chiTwo) public{
        childOne = chiOne;
        childTwo = chiTwo;
        creator = msg.sender;
    }
    
    function changeChildOne(address chiOne) public{
        require(msg.sender==creator);
        childOne = chiOne;
    }
    
    function changeChildTwo(address chiTwo) public{
        require(msg.sender==creator);
        childTwo = chiTwo;
    }
    
    function give() external payable{
            uint equalSplit = msg.value/2;
            childOne.transfer(equalSplit);
            childTwo.transfer(equalSplit);
            etherSpent+=msg.value;
    }
}