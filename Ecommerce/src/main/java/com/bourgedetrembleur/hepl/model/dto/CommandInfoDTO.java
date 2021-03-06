package com.bourgedetrembleur.hepl.model.dto;

public class CommandInfoDTO
{
    private int commandId;
    private String status;
    private float amount = 0;
    private boolean payed;

    public CommandInfoDTO()
    {

    }

    public int getCommandId()
    {
        return commandId;
    }

    public void setCommandId(int commandId)
    {
        this.commandId = commandId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public boolean isPayed()
    {
        return payed;
    }

    public void setPayed(boolean payed)
    {
        this.payed = payed;
    }
}
