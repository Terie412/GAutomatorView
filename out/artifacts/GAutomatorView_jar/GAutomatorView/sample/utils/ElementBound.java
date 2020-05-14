package sample.utils;

public class ElementBound {
    public float x;
    public float y;
    public float width;
    public float height;
    public boolean visible;
    public ElementBound(float x, float y, float width, float height, boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }

    public boolean ifCoordinationInBound(float x, float y)
    {
        float centerX = this.x;
        float centerY = this.y;
        if(visible && x >= centerX && x <= centerX + width && y >= centerY && y <=centerY + height)
        {
            return true;
        }
        return false;
    }

    public String getLocationInfo()
    {
        return String.format("%d, %d", (int)x, (int)y);
    }

    public String getBounds()
    {
        return String.format("宽: %d, 高: %d, Visible: %b", (int)width, (int)height, visible);
    }

    @Override
    public String toString() {
        return String.format("location=(%f ,%f) size=(%f %f)",x,y,width,height);
    }
}
