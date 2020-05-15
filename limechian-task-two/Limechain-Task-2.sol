pragma solidity ^0.4.17;

contract Limechain{
    address private childOne;
    address private childTwo;
    uint private etherSpent;
    
    function Limechain(address chiOne, address chiTwo) public{
        childOne = chiOne;
        childTwo = chiTwo;
    }
    
    function getTotalEtherSpent() public view returns(uint){
        return etherSpent;
    }
    
    function give() external payable{
            uint equalSplit = msg.value/2;
            childOne.transfer(equalSplit);
            childTwo.transfer(equalSplit);
            etherSpent+=msg.value;
    }
}