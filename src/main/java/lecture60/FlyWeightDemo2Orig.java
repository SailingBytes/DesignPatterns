package lecture60;

import java.util.ArrayList;
import java.util.List;

class FormattedTextOrig
{
    private String plainText;
    private boolean [] capitalize;

    public FormattedTextOrig(String plainText)
    {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end)
    {
        for (int i = start; i <= end; ++i)
            capitalize[i] = true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); ++i)
        {
            char c = plainText.charAt(i);
            sb.append(capitalize[i] ? Character.toUpperCase(c) : c);
        }
        return sb.toString();
    }
}

class BetterFormattedTextOrig
{
    private String plainText;
    private List<TextRangeOrig> formatting = new ArrayList<>();

    public BetterFormattedTextOrig(String plainText)
    {
        this.plainText = plainText;
    }

    public TextRangeOrig getRange(int start, int end)
    {
        TextRangeOrig range = new TextRangeOrig(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); ++i)
        {
            char c = plainText.charAt(i);
            for (TextRangeOrig range : formatting)
                if (range.covers(i) && range.capitalize)
                    c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRangeOrig
    {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRangeOrig(int start, int end)
        {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position)
        {
            return position >= start && position <= end;
        }
    }
}

class FlyWeightDemo2Orig
{
    public static void main(String[] args)
    {
        FormattedTextOrig ft = new FormattedTextOrig("This is a brave new world");
        ft.capitalize(10, 15);
        System.out.println(ft);

        BetterFormattedTextOrig bft = new BetterFormattedTextOrig("Make America Great Again");
        bft.getRange(13, 18).capitalize = true;
        System.out.println(bft);
    }
}
