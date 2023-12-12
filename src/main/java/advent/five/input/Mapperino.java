package advent.five.input;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Mapperino {

    private static final String DELIMITER_NEW_LINE = "\n";

    private Map<Long, Long> seedMap = new HashMap<>();
    private List<Long> seedList = new ArrayList<>();
    private List<String> allAlmanacLines = new ArrayList<>();

    public void setSeedToSoilMap() {
        addSeedsToMap(seedString);
        System.out.println("-- START --");
        String[] almanacLines = seedToSoilString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Seed To Soil --");

        almanacLines = soilToFertilizerString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Soil To Fertilizer --");
        almanacLines = fertilizerToWaterString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Fertilizer To Water --");


        almanacLines = waterToLightString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Water To Light --");

        almanacLines = lightToTemperatureString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Light To Temperature --");


        almanacLines = temperatureToHumidityString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Temperature To Humidity --");

        almanacLines = humidityToLocationString.split(DELIMITER_NEW_LINE);
        trackSeedsAccordingToAlmanac(almanacLines, seedMap);
        System.out.println("-- Humidity To Location --");

        // 323142486
        findTheSmallestLocation();
    }

    public void setSeedRange() {
        seedRange(seedString);
        long smallestSeedLocation = Long.MAX_VALUE;
        long temp = 0L;
        for(int i = 0; i < seedList.size(); i += 2) {
            long seedStart = seedList.get(i);
            long seedEnd = seedStart + seedList.get((i + 1)) - 1;
            System.out.println("***" + seedStart);
            for (long j = seedStart; j <= seedEnd; j = j + 1000000) {
                temp = j;
                for (String almanacLine: allAlmanacLines) {
                    String[] almanac = almanacLine.split(DELIMITER_NEW_LINE);
                    temp = trackSeedRangeAccordingToAlmanac(almanac, temp);
                }
                if(temp < smallestSeedLocation)
                    smallestSeedLocation = temp;
            }
            System.out.println(temp + "**");
        }
        System.out.println("Smallest Location is: " + smallestSeedLocation);
    }

    private void addSeedsToMap(String seedLine) {
        String[] seeds = seedLine.split(" ");
        for (String seed: seeds) {
            Long val = Long.parseLong(seed);
            seedMap.put(val, val);
        }
    }

    private void seedRange(String seedLine) {
        String[] seeds = seedLine.split(" ");
        for (String seed: seeds) {
            seedList.add(Long.parseLong(seed));
        }
        allAlmanacLines.add(seedToSoilString);
        allAlmanacLines.add(soilToFertilizerString);
        allAlmanacLines.add(fertilizerToWaterString);
        allAlmanacLines.add(waterToLightString);
        allAlmanacLines.add(lightToTemperatureString);
        allAlmanacLines.add(temperatureToHumidityString);
        allAlmanacLines.add(humidityToLocationString);
    }

    private long trackSeedRangeAccordingToAlmanac(String[] almanacLines, long input) {
        long output = input;
        for (String line: almanacLines) {
            String[] split = line.split(" ");

            long source = Long.parseLong(split[1]);
            long target = Long.parseLong(split[0]);
            long range = Long.parseLong(split[2]);

                if(source <= input && source + range >= input) {
                    output = input - source + target;
                    break;
                }
        }
        return output;
    }

    private void trackSeedsAccordingToAlmanac(String[] almanacLines, Map<Long, Long> seedMap) {

        List<Long> changedSeeds = new ArrayList<>();

        for (String line: almanacLines) {
            String[] split = line.split(" ");

            long source = Long.parseLong(split[1]);
            long target = Long.parseLong(split[0]);
            long range = Long.parseLong(split[2]);

            for (Map.Entry<Long,Long> seed: seedMap.entrySet()) {
                if(source <= seed.getValue() && source + range >= seed.getValue()) {
                    if(changedSeeds.contains(seed.getKey()))
                        continue;
                    long translatedIndex = seed.getValue() - source + target;
                    seedMap.put(seed.getKey(), translatedIndex);
                    changedSeeds.add(seed.getKey());
                }
            }
        }
        printSeedMap();
    }

    private void printSeedMap() {
        for (Map.Entry<Long,Long> seed: seedMap.entrySet()) {
            System.out.println("Seed Key: " + seed.getKey() + " Seed Value: " + seed.getValue());
        }
    }

    private void findTheSmallestLocation() {
        long smallestLocation = Long.MAX_VALUE;
        for (Map.Entry<Long,Long> seed: seedMap.entrySet()) {
            if(seed.getValue() < smallestLocation)
                smallestLocation = seed.getValue();
        }
        System.out.println("Smallest Location: " + smallestLocation);
    }

    @Getter
    private String seedString = "2637529854 223394899 3007537707 503983167 307349251 197383535 3543757609 276648400 2296792159 141010855 116452725 5160533 2246652813 49767336 762696372 160455077 3960442213 105867001 1197133308 38546766";
    @Getter
    private String seedStringExample = "79 14 55 13";

    private String seedToSoilString =
            "1024364543 1121869540 764570177\n" +
            "1788934720 0 30748436\n" +
            "710359306 576061773 314005237\n" +
            "1819683156 509305212 66756561\n" +
            "478556776 890067010 231802530\n" +
            "0 30748436 478556776";
    private String seedToSoilStringExample =
            "50 98 2\n" +
            "52 50 48";
    private String soilToFertilizerString =
            "3998185854 3762642503 103735883\n" +
            "2968507762 2068943953 132922295\n" +
            "1615660383 3128249668 130118355\n" +
            "730037950 2201866248 85080413\n" +
            "30504699 101283437 22743985\n" +
            "1963198232 3098529992 29719676\n" +
            "2445396241 816829001 206574911\n" +
            "4101921737 3258368023 13795434\n" +
            "1745778738 3272163457 98082636\n" +
            "184429295 124027422 42778761\n" +
            "815118363 1688321922 16510291\n" +
            "227208056 0 101283437\n" +
            "3101430057 730037950 86791051\n" +
            "2855369613 1073802717 8286129\n" +
            "2726882317 4266444706 28522590\n" +
            "2755404907 1647814534 40507388\n" +
            "1992917908 4002766747 184714231\n" +
            "0 307861200 20630293\n" +
            "3437715786 2286946661 488868406\n" +
            "2212191740 2910044568 188485424\n" +
            "1875478802 3756833622 5808881\n" +
            "1386084226 4200760960 31124145\n" +
            "4115717171 1704832213 179250125\n" +
            "2177632139 4231885105 34559601\n" +
            "968017015 3370246093 57414778\n" +
            "1957518504 1068122989 5679728\n" +
            "20630293 205631872 9874406\n" +
            "1248075951 2775815067 134229501\n" +
            "1417208371 1449362522 198452012\n" +
            "145603606 166806183 38825689\n" +
            "3926584192 1318303542 71601662\n" +
            "2651971152 3650305029 74911165\n" +
            "2863655742 1884082338 104852020\n" +
            "2400677164 1023403912 44719077\n" +
            "1025431793 3427660871 222644158\n" +
            "53248684 215506278 92354922\n" +
            "2795912295 1389905204 59457318\n" +
            "831628654 3866378386 136388361\n" +
            "1382305452 2065165179 3778774\n" +
            "1843861374 3725216194 31617428\n" +
            "3188221108 1082088846 236214696\n" +
            "1881287683 1988934358 76230821\n" +
            "3424435804 4187480978 13279982";
    private String soilToFertilizerStringExample =
            "0 15 37\n" +
            "37 52 2\n" +
            "39 0 15\n";

    private String fertilizerToWaterString =
            "52426778 0 42548209\n" +
            "2317094055 2628693938 80884528\n" +
            "4273469923 2317094055 21497373\n" +
            "3504443816 3801741356 19441920\n" +
            "1168510873 722677906 36277136\n" +
            "766143928 2147127897 113763545\n" +
            "2859981871 4203009036 91958260\n" +
            "3742911607 2903448576 530558316\n" +
            "2533492330 3494200839 307540517\n" +
            "1048443265 602610298 120067608\n" +
            "1606177181 2049845518 37728052\n" +
            "0 2087573570 24184027\n" +
            "2228508217 539688172 62922126\n" +
            "3523885736 4014822611 112866625\n" +
            "3636752361 3821183276 106159246\n" +
            "2204777013 515956968 23731204\n" +
            "163272748 80266080 177461896\n" +
            "340734644 1624436234 425409284\n" +
            "1568459310 42548209 37717871\n" +
            "3039420220 2338591428 290102510\n" +
            "1336392351 257727976 5651080\n" +
            "2397978583 3434006892 60193947\n" +
            "2951940131 3927342522 87480089\n" +
            "94974987 341413667 68297761\n" +
            "1750150773 263379056 78034611\n" +
            "1932038162 1351697383 272738851\n" +
            "1828185384 897499179 73313877\n" +
            "1563080665 758955042 5378645\n" +
            "1643905233 409711428 106245540\n" +
            "3329522730 2799034919 104413657\n" +
            "1901499261 2260891442 30538901\n" +
            "879907473 764333687 133165492\n" +
            "1342043431 1130660149 221037234\n" +
            "2458172530 4127689236 75319800\n" +
            "2841032847 2709578466 18949024\n" +
            "24184027 1102417398 28242751\n" +
            "1204788009 970813056 131604342\n" +
            "1013072965 2111757597 35370300\n" +
            "3433936387 2728527490 70507429";
    private String fertilizerToWaterStringExample =
            "49 53 8\n" +
            "0 11 42\n" +
            "42 0 7\n" +
            "57 7 4";
    private String waterToLightString =
            "71899121 201997255 39056119\n" +
            "1870747295 791060221 18793056\n" +
            "1109877678 2647277659 20201672\n" +
            "496126127 1366401345 54786206\n" +
            "2289070096 3946384506 79288366\n" +
            "2872432379 3633289978 245210479\n" +
            "2566896529 1931266150 305535850\n" +
            "2094153218 1065408320 14844701\n" +
            "1747071839 1759996387 105111897\n" +
            "3528644588 1080253021 193678883\n" +
            "675287597 4169571318 125395978\n" +
            "1852183736 3256553708 6224195\n" +
            "611190115 1486911572 64097482\n" +
            "1972384294 809853277 55611058\n" +
            "4292231732 3217343431 613168\n" +
            "1498491937 2667479331 11999801\n" +
            "1356554237 502551806 141937700\n" +
            "110955240 241053374 38164682\n" +
            "800683575 2316420571 309194103\n" +
            "2368358462 2236802000 62837191\n" +
            "2523665094 3878500457 34541659\n" +
            "3488154933 3913042116 10582322\n" +
            "567693713 1443415170 43496402\n" +
            "2558206753 3247863932 8689776\n" +
            "4065356099 2990467798 226875633\n" +
            "3992305706 2625614674 21662985\n" +
            "4013968691 739672813 51387408\n" +
            "1330023335 1421187551 22227619\n" +
            "550912333 2299639191 16781380\n" +
            "1352250954 496126127 4303283\n" +
            "2027995352 1865108284 66157866\n" +
            "1889540351 644489506 82843943\n" +
            "3117642858 3262777903 370512075\n" +
            "3498737255 3217956599 29907333\n" +
            "2431195653 1273931904 92469441\n" +
            "1858407931 727333449 12339364\n" +
            "0 130098134 71899121\n" +
            "2170307311 2679479132 41006431\n" +
            "2211313742 4025672872 54996286\n" +
            "4292844900 500429410 2122396\n" +
            "149119922 0 130098134\n" +
            "1719479071 4141978550 27592768\n" +
            "3722323471 2720485563 269982235\n" +
            "2266310028 3923624438 22760068\n" +
            "2108997919 4080669158 61309392\n" +
            "1510491738 1551009054 208987333\n" +
            "1130079350 865464335 199943985";
    private String waterToLightStringExample =
            "88 18 7\n" +
            "18 25 70";

    private String lightToTemperatureString =
            "2246981140 2123929713 26608756\n" +
            "506825382 1211085022 100001399\n" +
            "2273589896 3259651351 49795378\n" +
            "606826781 48305627 403771586\n" +
            "2441004040 1311086421 255128583\n" +
            "2696132623 887597567 323487455\n" +
            "3131241320 452077213 69964335\n" +
            "1483012724 2119418493 4511220\n" +
            "4100438569 4209824659 85142637\n" +
            "2046786877 3309446729 95808859\n" +
            "1487523944 2344091207 205790126\n" +
            "3125128781 522041548 6112539\n" +
            "74735386 1566215004 432089996\n" +
            "2323385274 2937379483 117618766\n" +
            "46634343 2549881333 28101043\n" +
            "1215251469 1998305000 118258255\n" +
            "3201205655 0 1671284\n" +
            "3202876939 2150538469 193552738\n" +
            "1010598367 3054998249 204653102\n" +
            "3019620078 2577982376 105508703\n" +
            "2142595736 2832994079 104385404\n" +
            "1333509724 2683491079 149503000\n" +
            "4185581206 4100438569 109386090\n" +
            "1693314070 534124760 353472807\n" +
            "3396429677 528154087 5970673\n" +
            "0 1671284 46634343\n" +
            "3402400350 2116563255 2855238";
    private String lightToTemperatureStringExample =
            "45 77 23\n" +
            "81 45 19\n" +
            "68 64 13";
    private String temperatureToHumidityString =
            "3585785215 3115915735 709182081\n" +
            "1595400550 915086594 131894638\n" +
            "3115915735 3825097816 469869480\n" +
            "257043810 1628903919 98391269\n" +
            "355435079 1106926810 521977109\n" +
            "877412188 0 717988362\n" +
            "59945578 717988362 197098232\n" +
            "0 1046981232 59945578";
    private String temperatureToHumidityStringExample =
            "0 69 1\n" +
            "1 0 69";
    private String humidityToLocationString =
            "2826523858 2649651094 205250361\n" +
            "2028276378 1553868404 798247480\n" +
            "225543770 4100328990 5722049\n" +
            "595208874 2352115884 297535210\n" +
            "3031774219 2854901455 205611797\n" +
            "1267642800 3435411968 22064409\n" +
            "138173954 4119345931 87369816\n" +
            "231265819 49632084 363943055\n" +
            "1877617809 1403209835 150658569\n" +
            "0 413575139 138173954\n" +
            "3237386016 3457476377 642852613\n" +
            "892744084 3060513252 374898716\n" +
            "3893533521 551749093 313182226\n" +
            "1339339293 864931319 538278516\n" +
            "1289707209 0 49632084\n" +
            "3880238629 4106051039 13294892";
    private String humidityToLocationStringExample =
            "60 56 37\n" +
            "56 93 4";
}
