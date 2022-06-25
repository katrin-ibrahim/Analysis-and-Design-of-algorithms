import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ElectricNetworkTests {
    int[] msts = {1, 2297, 2935, 7, 845, 2686, 99, 2713, 3576, 9587, 7045, 4818, 937, 15734, 10073, 52219, 23882, 22342, 10822};
    String[] tests = 
        {
        "T1,T2,1;" , 
        "T7,T5,660;T8,T3,352;T6,T7,723;T6,T4,889;T3,T6,201;T6,T1,358;T4,T7,269;T1,T8,764;T7,T3,185;T2,T7,794;T2,T1,272;T4,T3,763;" , 
        "T9,T5,149;T9,T8,443;T8,T4,496;T6,T4,586;T1,T8,28;T7,T8,380;T7,T3,932;T10,T7,609;T9,T3,976;T7,T10,277;T7,T2,286;T10,T8,231;T10,T9,216;T2,T1,799;T3,T9,666;",
        "T8,T7,1;T5,T8,1;T6,T5,1;T1,T7,1;T1,T6,1;T3,T8,1;T5,T2,1;T2,T7,1;T2,T8,1;T4,T5,1;T3,T4,1;",
        "T5,T6,229;T5,T4,104;T4,T3,108;T3,T1,746;T1,T3,227;T1,T2,177;T4,T1,433;T4,T6,792;T3,T6,549;T2,T1,967;",
        "T6,T4,727;T7,T1,814;T7,T4,517;T4,T1,333;T2,T1,264;T2,T6,305;T6,T1,458;T5,T2,356;T1,T4,602;T3,T5,911;",
        "T7,T21,1;T7,T91,1;T21,T35,1;T35,T73,1;T73,T23,1;T23,T1,1;T91,T85,1;T1,T56,1;T56,T49,1;T35,T98,1;T85,T72,1;T21,T4,1;T1,T69,1;T91,T25,1;T69,T22,1;T35,T19,1;T4,T90,1;T72,T33,1;T91,T11,1;T98,T92,1;T49,T43,1;T56,T93,1;T4,T44,1;T22,T87,1;T1,T28,1;T90,T46,1;T46,T40,1;T35,T26,1;T49,T71,1;T85,T15,1;T15,T3,1;T85,T34,1;T4,T36,1;T92,T14,1;T28,T68,1;T15,T94,1;T25,T88,1;T85,T64,1;T3,T16,1;T36,T58,1;T92,T45,1;T87,T86,1;T34,T75,1;T64,T5,1;T21,T9,1;T40,T67,1;T19,T53,1;T75,T8,1;T53,T38,1;T43,T27,1;T46,T96,1;T86,T97,1;T87,T2,1;T33,T6,1;T93,T31,1;T31,T60,1;T35,T59,1;T64,T95,1;T6,T57,1;T58,T100,1;T34,T70,1;T11,T62,1;T69,T61,1;T93,T41,1;T91,T99,1;T5,T78,1;T41,T66,1;T3,T42,1;T75,T84,1;T5,T55,1;T3,T17,1;T35,T54,1;T17,T50,1;T43,T51,1;T19,T63,1;T96,T76,1;T51,T20,1;T57,T39,1;T45,T32,1;T87,T74,1;T66,T47,1;T46,T24,1;T41,T37,1;T55,T10,1;T39,T48,1;T43,T82,1;T100,T79,1;T84,T65,1;T7,T18,1;T39,T30,1;T98,T77,1;T46,T80,1;T85,T12,1;T90,T89,1;T87,T83,1;T66,T29,1;T53,T52,1;T34,T81,1;T20,T13,1;",
        "T5,T6,603;T2,T5,259;T8,T7,801;T8,T4,324;T1,T9,761;T10,T7,743;T3,T4,532;T6,T8,93;T10,T4,113;T5,T4,720;T7,T2,587;T10,T6,934;T7,T1,129;T10,T8,405;T8,T5,57;T10,T3,841;T4,T10,327;T10,T1,715;T2,T1,445;T10,T2,759;",
        "T1,T7,190;T7,T4,263;T4,T6,4;T4,T5,233;T4,T3,654;T6,T2,609;T6,T10,488;T4,T8,432;T3,T9,703;",
        "T14,T4,186;T14,T10,180;T14,T18,219;T18,T17,818;T4,T12,268;T4,T11,78;T18,T2,232;T4,T19,329;T17,T1,781;T12,T8,282;T11,T3,443;T14,T6,688;T14,T15,541;T10,T5,970;T6,T16,644;T12,T20,970;T19,T7,687;T5,T9,308;T8,T13,963;",
        "T13,T19,411;T15,T4,998;T15,T20,70;T5,T14,827;T8,T14,542;T17,T8,316;T11,T12,132;T16,T2,390;T15,T16,514;T4,T16,919;T2,T11,637;T16,T13,642;T5,T19,898;T11,T4,315;T14,T7,82;T17,T9,610;T9,T2,639;T9,T4,288;T13,T10,271;T14,T16,729;T4,T17,465;T5,T17,77;T8,T3,738;T7,T14,992;T9,T7,571;T18,T13,739;T14,T13,764;T6,T1,489;T1,T20,237;T8,T6,327;",
        "T2,T11,599;T8,T19,464;T14,T8,634;T5,T18,922;T14,T17,332;T20,T2,831;T13,T4,683;T20,T7,665;T17,T11,805;T11,T2,611;T10,T3,877;T19,T12,773;T7,T4,979;T4,T14,388;T20,T14,399;T3,T2,861;T14,T16,393;T12,T8,552;T5,T12,142;T1,T19,85;T10,T6,30;T16,T19,685;T16,T9,255;T13,T20,319;T19,T20,764;T9,T11,90;T16,T17,206;T1,T13,534;T4,T13,559;T6,T12,816;T9,T10,881;T16,T15,280;T4,T20,131;T3,T16,559;T2,T4,40;T15,T14,207;T12,T10,884;T3,T20,760;T6,T18,571;T10,T16,788;T10,T1,374;T8,T1,23;T7,T19,32;",
        "T13,T10,954;T8,T16,55;T20,T18,16;T13,T6,78;T2,T11,947;T12,T13,31;T10,T12,824;T2,T18,673;T18,T20,711;T7,T9,626;T9,T10,254;T1,T3,384;T7,T3,666;T10,T11,259;T13,T20,951;T14,T12,842;T17,T14,248;T11,T5,601;T6,T19,614;T17,T11,844;T7,T11,943;T18,T10,766;T19,T16,639;T1,T14,84;T15,T3,435;T11,T13,186;T5,T14,299;T9,T14,415;T16,T13,299;T1,T16,436;T14,T4,647;T6,T10,61;T10,T8,167;T11,T7,601;T9,T6,408;T2,T6,511;T18,T7,121;T12,T2,826;T17,T8,927;T13,T17,215;T17,T5,4;T9,T17,439;T3,T13,60;T19,T13,625;T11,T16,361;T2,T19,500;T1,T17,447;T7,T16,536;T18,T9,515;T2,T7,449;T2,T10,789;T13,T11,620;T15,T20,982;T1,T9,740;T20,T14,432;T11,T8,944;T12,T8,955;T11,T14,732;T13,T3,870;T1,T2,146;T15,T9,317;T19,T10,400;T15,T16,672;T5,T13,414;T1,T8,387;T12,T1,684;T15,T2,739;T2,T4,789;T6,T17,880;T8,T15,488;T6,T9,204;T13,T16,938;T9,T3,70;T12,T3,497;T15,T11,811;T12,T7,764;T11,T4,403;T7,T17,10;T11,T19,413;T7,T4,18;T13,T4,927;T14,T10,41;T8,T1,650;T8,T3,140;T6,T8,817;T11,T17,216;T7,T12,159;T2,T5,36;T20,T5,486;T10,T20,865;T7,T2,131;T20,T19,532;T8,T10,42;T20,T1,835;T4,T8,590;T7,T20,40;T12,T18,759;T3,T10,642;T18,T14,862;T9,T19,514;T4,T2,897;T5,T12,467;T5,T1,112;T5,T2,761;T11,T12,657;T1,T15,310;T8,T5,91;T5,T16,222;T14,T2,889;T18,T2,34;T7,T18,844;T19,T11,6;T5,T6,489;T18,T3,375;T12,T6,485;T11,T9,278;T7,T6,266;T9,T7,665;T18,T8,480;T6,T5,407;T13,T12,934;T19,T2,207;T15,T17,294;T15,T13,248;T3,T4,130;T3,T20,681;T15,T19,702;T6,T4,993;T19,T18,133;T16,T6,468;T17,T9,244;T5,T15,801;T12,T5,626;T14,T3,717;T4,T20,127;T3,T16,373;T14,T6,146;T10,T3,318;T9,T11,486;T20,T13,277;T2,T16,885;T6,T16,490;T13,T2,113;T10,T17,164;T19,T9,693;T13,T19,802;T20,T17,357;T7,T8,161;T14,T1,209;T8,T18,268;T8,T19,317;T13,T15,859;T20,T2,690;T4,T11,500;T14,T7,46;T9,T13,145;T20,T4,39;T18,T5,411;T3,T18,80;T20,T3,1;T20,T8,448;T9,T18,726;T13,T7,401;T10,T15,247;T4,T14,776;T11,T15,206;T3,T19,308;T12,T17,43;T3,T5,247;T18,T17,949;T5,T11,465;T20,T12,337;T6,T18,151;T9,T16,858;T12,T19,750;T10,T5,86;T1,T11,803;T4,T17,580;T1,T10,111;T12,T11,686;T10,T19,776;T7,T13,255;T9,T15,471;T2,T20,670;T2,T1,491;T10,T7,76;T18,T1,719;T18,T19,298;T16,T1,344;T4,T9,102;",
        "T33,T48,330;T12,T20,249;T39,T15,776;T19,T15,400;T10,T24,311;T16,T42,702;T34,T10,986;T36,T15,988;T32,T20,758;T50,T47,445;T19,T24,944;T27,T29,733;T50,T45,564;T30,T26,768;T40,T15,189;T22,T15,552;T15,T42,439;T14,T48,243;T27,T19,743;T2,T3,807;T17,T48,908;T12,T31,846;T26,T37,839;T4,T14,102;T48,T40,448;T46,T37,987;T27,T5,903;T30,T3,972;T45,T7,609;T41,T23,200;T22,T42,688;T23,T31,512;T24,T45,239;T50,T10,978;T23,T14,41;T3,T47,817;T50,T4,35;T46,T28,455;T5,T4,758;T10,T17,537;T24,T28,634;T37,T4,956;T38,T18,465;T27,T33,545;T13,T18,581;T33,T23,966;T22,T47,920;T31,T34,32;T14,T32,365;T30,T15,949;T38,T8,726;T18,T3,49;T5,T21,54;T37,T14,6;T45,T46,430;T45,T9,457;T28,T21,379;T41,T16,260;T27,T24,12;T20,T11,77;T21,T44,163;T20,T21,873;T26,T35,371;T10,T22,706;T26,T6,234;T2,T30,280;T15,T12,623;T26,T20,54;T3,T39,50;T9,T11,450;T12,T39,161;T2,T43,30;T7,T25,185;T38,T28,989;T19,T49,517;T18,T1,436;T19,T29,538;T29,T14,408;T9,T46,322;T18,T22,840;",
        "T32,T23,639;T4,T24,789;T16,T31,211;T49,T22,235;T3,T16,379;T13,T36,448;T44,T32,146;T12,T27,454;T24,T41,249;T42,T14,40;T26,T38,725;T47,T3,924;T35,T18,31;T26,T18,450;T14,T10,574;T33,T5,775;T35,T24,917;T40,T26,46;T24,T9,452;T11,T36,6;T10,T18,783;T25,T47,346;T28,T33,674;T6,T20,391;T49,T50,87;T39,T2,744;T7,T10,636;T8,T37,876;T49,T4,810;T21,T17,774;T40,T31,965;T3,T39,808;T21,T28,367;T17,T43,503;T28,T46,340;T2,T40,751;T3,T6,778;T18,T20,1;T36,T9,525;T26,T11,119;T11,T27,515;T30,T28,653;T21,T8,735;T43,T11,293;T44,T36,360;T5,T27,950;T5,T44,194;T13,T26,950;T40,T41,170;T50,T1,722;T35,T44,735;T31,T11,952;T4,T30,327;T46,T2,618;T50,T2,931;T32,T24,361;T18,T13,43;T39,T49,616;T14,T2,413;T38,T9,111;T12,T35,882;T5,T20,71;T23,T15,910;T45,T19,436;T18,T19,938;T40,T22,490;T20,T7,793;T10,T16,395;T5,T21,282;T37,T26,699;T43,T29,124;T50,T8,134;T14,T19,186;T28,T2,392;T26,T5,999;T32,T42,106;T15,T14,927;T27,T49,14;T32,T26,891;T37,T42,162;T15,T43,373;T23,T9,947;T27,T20,535;T32,T1,577;T4,T31,10;T50,T27,922;T29,T12,53;T43,T34,961;T42,T13,766;T33,T44,454;T43,T16,897;T42,T9,782;T1,T48,5;T13,T11,706;T35,T49,206;T22,T40,801;T21,T31,508;T24,T35,660;T22,T38,269;T47,T25,304;T50,T16,145;T34,T5,537;T5,T41,805;T6,T3,866;T8,T4,566;T50,T17,158;T25,T2,755;T5,T30,16;T9,T31,565;T26,T25,119;T2,T35,115;T7,T36,323;T26,T21,673;T46,T1,206;T43,T21,355;T29,T32,156;T2,T9,726;T6,T40,854;T18,T1,367;T28,T4,909;",
        "T19,T79,576;T79,T99,926;T19,T57,881;T79,T43,782;T57,T25,500;T43,T65,867;T79,T58,146;T79,T45,568;T45,T87,513;T25,T74,472;T79,T90,225;T58,T94,690;T58,T1,198;T79,T51,758;T19,T6,955;T25,T77,193;T6,T75,740;T99,T100,709;T65,T13,319;T99,T64,52;T75,T68,685;T68,T22,717;T75,T78,360;T51,T69,620;T22,T53,49;T64,T50,240;T43,T36,315;T25,T56,639;T1,T16,320;T65,T24,908;T51,T73,396;T16,T7,657;T57,T85,535;T53,T60,360;T22,T98,119;T87,T63,982;T75,T84,351;T6,T33,92;T36,T76,796;T57,T52,517;T100,T29,782;T58,T3,873;T77,T32,227;T52,T46,212;T68,T55,872;T100,T21,337;T46,T81,452;T78,T23,198;T13,T17,783;T13,T93,862;T81,T9,498;T7,T47,307;T19,T82,26;T100,T61,805;T73,T14,728;T53,T48,815;T87,T91,871;T55,T10,899;T43,T28,143;T58,T4,873;T51,T71,198;T6,T37,721;T57,T5,620;T52,T67,862;T50,T8,737;T60,T72,333;T100,T59,573;T23,T49,739;T48,T92,491;T33,T34,47;T52,T27,14;T33,T86,936;T74,T31,42;T60,T70,35;T8,T41,502;T37,T40,592;T19,T89,953;T64,T97,346;T1,T83,539;T14,T39,758;T86,T66,32;T86,T12,650;T4,T96,758;T41,T95,789;T34,T15,122;T100,T2,731;T71,T35,989;T35,T54,683;T65,T42,713;T95,T30,878;T2,T38,334;T84,T11,56;T37,T44,423;T86,T26,322;T58,T80,639;T96,T62,127;T3,T20,507;T94,T18,188;T35,T88,949;",
        "T7,T59,661;T76,T80,222;T82,T47,209;T67,T87,615;T16,T81,981;T60,T10,748;T1,T77,843;T13,T86,582;T38,T97,361;T78,T62,61;T51,T24,269;T16,T69,675;T17,T34,698;T94,T61,182;T94,T66,860;T79,T29,113;T94,T19,608;T59,T89,25;T53,T89,320;T84,T15,771;T81,T87,888;T5,T11,21;T85,T35,412;T5,T86,5;T58,T82,400;T65,T15,924;T2,T10,485;T32,T33,240;T97,T92,514;T77,T69,358;T68,T17,615;T64,T100,807;T49,T41,348;T34,T78,647;T86,T70,362;T13,T39,303;T44,T66,397;T56,T78,288;T63,T84,904;T3,T87,216;T39,T75,676;T70,T71,948;T62,T57,470;T38,T22,680;T4,T41,262;T7,T16,662;T65,T46,878;T8,T73,641;T69,T35,94;T49,T78,608;T6,T87,199;T9,T98,789;T10,T73,706;T28,T37,526;T76,T37,193;T98,T42,762;T9,T32,326;T53,T82,8;T11,T59,825;T100,T83,527;T28,T50,781;T40,T25,526;T12,T13,199;T8,T62,18;T73,T41,568;T58,T17,357;T22,T36,403;T52,T82,695;T8,T39,989;T2,T13,207;T98,T87,152;T34,T76,859;T75,T99,788;T53,T27,681;T7,T99,460;T20,T17,543;T7,T67,542;T63,T61,97;T75,T38,733;T63,T32,74;T69,T63,427;T67,T15,45;T62,T95,762;T47,T8,587;T59,T17,458;T56,T29,260;T31,T9,216;T81,T94,389;T68,T60,525;T53,T66,296;T64,T67,79;T27,T97,485;T46,T24,648;T45,T75,980;T94,T2,234;T51,T40,636;T46,T63,79;T84,T74,593;T88,T70,187;T79,T38,483;T81,T5,847;T67,T85,503;T93,T54,548;T88,T71,926;T60,T20,307;T34,T5,617;T37,T85,903;T28,T16,850;T63,T80,907;T65,T58,170;T75,T64,488;T4,T68,574;T51,T73,653;T4,T99,184;T64,T12,129;T98,T30,422;T13,T29,492;T55,T57,588;T39,T30,940;T85,T90,651;T99,T2,876;T12,T52,363;T6,T57,239;T19,T37,349;T79,T72,112;T60,T16,294;T66,T20,861;T23,T24,744;T37,T4,160;T61,T13,761;T67,T58,425;T32,T57,247;T96,T25,803;T76,T25,382;T60,T89,470;T36,T45,334;T68,T72,386;T100,T8,696;T35,T29,978;T67,T49,833;T28,T6,835;T3,T75,435;T51,T56,208;T42,T77,108;T85,T96,862;T90,T71,300;T89,T38,2;T1,T95,59;T22,T85,160;T31,T67,686;T98,T1,985;T4,T64,792;T24,T74,206;T58,T61,646;T91,T82,627;T41,T79,61;T86,T71,96;T90,T94,659;T21,T35,498;T3,T44,10;T89,T53,704;T28,T84,480;T57,T96,425;T43,T31,823;T77,T61,646;T37,T65,131;T32,T60,650;T9,T29,21;T3,T94,189;T47,T87,58;T91,T24,675;T31,T73,470;T10,T64,810;T82,T89,413;T84,T25,322;T24,T35,378;T47,T27,492;T22,T42,949;T75,T85,185;T42,T45,912;T41,T14,895;T34,T85,575;T92,T28,683;T18,T10,771;T88,T91,982;T87,T24,331;T73,T2,150;T41,T70,650;T42,T16,384;T36,T59,888;T94,T98,477;T7,T24,901;T74,T7,251;T65,T73,746;T32,T53,455;T55,T18,367;T46,T39,392;T60,T67,338;T35,T83,652;T51,T93,928;T9,T57,431;T97,T12,177;T10,T79,622;T65,T1,810;T40,T82,800;T13,T93,893;T100,T39,383;T1,T56,11;T34,T35,693;T16,T48,645;T78,T53,99;T97,T53,44;T10,T86,664;T55,T1,80;T4,T5,929;T96,T49,35;T47,T23,173;T12,T33,146;T74,T6,898;T74,T83,710;T43,T27,196;T48,T98,107;T66,T70,832;T37,T100,995;T81,T100,885;T71,T12,594;T66,T57,921;T26,T66,313;T15,T18,850;T34,T62,29;T79,T55,368;T24,T56,727;T49,T68,174;T49,T83,492;T74,T44,233;T6,T43,966;T5,T90,858;T85,T1,604;T72,T64,778;T62,T98,409;T13,T44,261;T12,T56,310;T32,T59,435;T76,T100,584;T42,T68,745;T32,T91,900;T28,T31,768;T99,T11,382;T44,T37,60;T31,T12,943;",
        "T91,T20,39;T4,T94,721;T17,T3,556;T65,T77,488;T81,T25,221;T80,T61,660;T24,T96,554;T63,T76,676;T85,T100,873;T99,T92,210;T75,T30,557;T86,T97,917;T3,T11,928;T2,T39,994;T66,T7,26;T27,T64,51;T72,T98,608;T10,T35,605;T11,T94,340;T95,T24,716;T94,T23,586;T50,T92,2;T59,T33,497;T63,T98,804;T25,T89,163;T15,T3,89;T97,T78,307;T13,T52,778;T65,T58,901;T99,T45,909;T24,T67,948;T48,T15,42;T54,T69,902;T6,T79,641;T87,T6,215;T53,T84,348;T24,T99,700;T38,T66,219;T81,T35,43;T63,T68,850;T65,T64,460;T86,T95,432;T83,T29,282;T41,T26,406;T7,T93,487;T67,T60,919;T17,T60,644;T62,T45,545;T68,T2,330;T80,T82,269;T2,T35,197;T22,T71,374;T3,T43,981;T14,T90,25;T87,T90,798;T62,T21,752;T59,T47,646;T80,T50,620;T90,T99,809;T28,T18,86;T10,T37,994;T67,T89,203;T55,T44,98;T32,T53,494;T26,T90,514;T40,T28,962;T54,T72,356;T54,T14,90;T46,T60,109;T82,T16,96;T73,T2,161;T21,T84,590;T55,T22,721;T56,T48,4;T9,T13,252;T47,T60,637;T91,T93,841;T33,T49,216;T48,T90,93;T65,T34,811;T55,T28,791;T83,T38,167;T54,T32,262;T90,T68,805;T26,T96,128;T59,T15,927;T47,T54,443;T81,T20,20;T21,T94,822;T51,T33,257;T54,T70,223;T31,T61,533;T4,T14,946;T75,T29,808;T2,T8,592;T97,T12,963;T34,T32,891;T46,T91,478;T68,T62,632;T33,T29,955;T39,T68,590;T16,T83,955;T61,T98,128;T31,T33,812;T15,T20,86;T81,T59,665;T93,T29,176;T49,T87,822;T86,T70,97;T90,T77,654;T21,T29,813;T22,T58,787;T30,T91,819;T93,T1,407;T66,T99,204;T54,T35,165;T16,T19,382;T8,T43,413;T75,T99,723;T23,T57,522;T87,T42,472;T58,T36,557;T91,T86,809;T42,T98,414;T66,T21,5;T24,T23,429;T64,T38,303;T66,T87,26;T42,T23,225;T32,T50,107;T2,T31,945;T91,T6,20;T17,T50,711;T47,T48,453;T62,T14,28;T4,T6,816;T91,T87,724;T35,T75,187;T85,T10,304;T36,T49,737;T74,T51,983;T6,T60,154;T55,T31,331;T51,T84,553;T91,T37,889;T21,T72,136;T71,T60,410;T19,T24,851;T24,T7,16;T57,T27,408;T15,T57,72;T54,T62,670;T58,T10,757;T85,T27,455;T83,T87,385;T36,T57,928;T50,T5,11;T47,T23,363;T77,T1,624;T13,T12,56;T96,T8,502;T14,T7,593;T88,T18,666;T71,T35,488;T73,T87,383;T14,T2,586;T37,T86,214;T56,T36,226;T73,T12,402;T92,T48,63;T40,T42,967;T40,T9,408;T85,T8,375;T45,T57,292;T14,T75,661;T2,T72,930;T87,T70,656;T38,T79,714;T46,T71,702;T86,T41,163;T4,T61,905;T29,T35,5;T83,T25,823;T64,T39,870;T93,T66,609;T85,T14,89;T31,T30,611;T95,T31,431;T67,T71,965;T3,T87,758;T59,T69,169;T40,T7,276;T90,T4,421;T56,T39,774;T64,T23,830;T6,T40,19;T3,T35,525;T54,T33,517;T67,T20,995;T11,T19,525;T82,T69,675;T55,T66,462;T30,T76,118;T60,T63,151;T70,T77,791;T99,T31,233;T41,T22,222;T76,T97,124;T27,T54,195;T10,T59,743;T83,T32,920;T62,T86,828;T26,T83,539;T52,T48,964;T32,T40,12;T4,T99,8;T82,T97,61;T23,T59,851;T86,T46,964;T39,T25,646;T8,T71,62;T45,T67,631;T78,T99,782;T75,T9,417;T99,T67,796;T78,T58,414;T21,T52,119;T9,T83,556;T2,T32,665;T57,T25,444;T58,T83,746;T14,T63,566;T72,T41,137;T90,T36,523;T31,T86,640;T60,T40,53;T96,T15,788;T25,T94,382;T93,T43,902;T54,T16,468;T51,T78,664;T15,T80,962;T20,T19,36;T2,T12,913;T37,T36,42;T27,T57,194;T88,T33,354;T58,T35,875;T19,T66,559;T94,T20,596;",
        "T78,T22,988;T38,T68,869;T98,T80,323;T22,T19,677;T33,T11,600;T15,T72,837;T90,T16,545;T88,T16,220;T3,T6,112;T100,T30,221;T87,T22,748;T11,T17,201;T78,T5,993;T39,T16,654;T84,T51,531;T67,T51,707;T97,T99,333;T92,T40,856;T64,T84,81;T88,T15,686;T45,T20,66;T23,T43,225;T50,T3,845;T32,T100,406;T53,T66,413;T16,T35,719;T39,T96,213;T31,T75,739;T20,T42,726;T63,T62,312;T39,T12,166;T35,T27,904;T25,T1,693;T91,T53,952;T28,T19,222;T68,T1,331;T97,T61,831;T67,T96,643;T32,T80,983;T13,T24,281;T80,T2,759;T67,T93,552;T12,T79,302;T66,T98,255;T57,T80,728;T33,T92,505;T64,T80,409;T11,T8,842;T15,T11,286;T88,T8,705;T5,T1,265;T69,T58,972;T58,T67,211;T52,T5,751;T31,T73,910;T58,T26,397;T79,T48,142;T17,T51,322;T75,T90,201;T69,T29,195;T21,T4,43;T46,T64,721;T29,T94,979;T11,T94,943;T99,T52,658;T91,T9,373;T75,T41,295;T66,T2,341;T23,T67,396;T47,T82,231;T12,T8,736;T83,T49,526;T84,T44,429;T41,T50,96;T81,T23,513;T84,T86,651;T25,T37,220;T62,T71,725;T48,T28,354;T97,T41,466;T91,T5,41;T68,T98,309;T56,T86,575;T55,T64,35;T49,T84,537;T95,T6,986;T45,T74,974;T64,T30,590;T36,T40,29;T33,T98,22;T65,T49,894;T31,T95,325;T30,T92,415;T91,T59,644;T42,T32,87;T4,T53,639;T7,T63,738;T52,T11,154;T23,T55,77;T44,T93,267;T54,T74,229;T59,T72,407;T46,T16,810;T30,T29,102;T72,T42,940;T44,T61,449;T68,T87,189;T99,T33,710;T85,T87,576;T35,T47,573;T28,T43,986;T11,T82,536;T95,T51,935;T21,T94,884;T86,T39,582;T21,T61,878;T86,T78,108;T21,T86,705;T49,T89,625;T72,T58,130;T60,T86,490;T37,T87,131;T94,T28,704;T25,T38,782;T88,T38,276;T52,T27,786;T95,T93,332;T47,T66,828;T72,T11,473;T13,T98,360;T16,T75,278;T84,T20,406;T24,T61,32;T73,T100,76;T6,T46,83;T74,T29,613;T81,T97,1;T93,T62,658;T38,T79,983;T90,T64,870;T37,T27,248;T17,T46,391;T89,T16,677;T19,T18,654;T15,T38,306;T94,T92,830;T15,T32,108;T31,T81,17;T16,T60,792;T71,T23,111;T15,T96,789;T94,T32,708;T46,T30,476;T69,T76,31;T95,T58,598;T4,T73,477;T67,T73,445;T38,T28,79;T23,T68,98;T11,T95,840;T37,T60,239;T4,T45,174;T99,T4,605;T66,T35,65;T7,T44,48;T3,T14,96;T19,T85,570;T30,T21,317;T99,T53,654;T9,T58,21;T94,T43,452;T100,T20,962;T53,T62,984;T87,T56,186;T63,T18,376;T64,T46,339;T61,T95,132;T40,T67,296;T18,T91,459;T12,T83,10;T92,T11,252;T70,T11,421;T37,T32,677;T79,T70,73;T41,T4,589;T96,T22,159;T77,T28,124;T98,T29,334;T73,T49,486;T77,T45,735;T47,T11,985;T82,T95,544;T72,T16,579;T19,T44,222;T82,T27,922;T6,T10,212;T44,T95,29;T20,T41,228;T6,T29,351;T84,T26,837;T3,T71,976;T62,T60,362;T38,T44,19;T66,T97,507;T90,T9,634;T58,T95,297;T13,T60,590;T85,T81,419;T12,T64,513;T83,T54,384;T14,T95,794;T94,T4,759;T31,T25,221;T84,T42,814;T52,T23,106;T20,T68,62;T85,T57,967;T19,T42,614;T32,T52,798;T76,T17,160;T60,T87,343;T9,T76,802;T22,T25,718;T65,T9,81;T35,T99,126;T72,T10,164;T69,T9,382;T22,T62,66;T58,T33,705;T13,T64,108;T50,T39,891;T63,T100,85;T91,T40,973;T35,T20,75;T2,T69,626;T45,T62,719;T7,T5,345;T46,T65,661;T87,T26,196;T21,T91,701;T99,T3,600;T4,T69,546;T73,T63,326;T35,T2,867;T88,T68,854;T65,T50,457;T99,T71,770;T2,T29,721;T83,T73,414;T49,T14,704;T15,T7,227;T14,T57,150;T68,T84,743;T12,T85,947;T48,T27,128;T87,T3,834;T60,T54,264;T96,T63,589;T61,T2,298;T81,T13,339;T7,T86,632;T82,T50,282;T32,T10,102;T3,T100,901;T11,T69,151;T96,T38,76;T64,T6,723;T93,T38,951;T7,T12,736;T60,T42,270;T59,T4,87;T31,T19,237;T75,T12,174;T52,T49,421;T74,T31,886;T35,T92,770;T7,T87,189;T14,T96,485;T45,T86,830;T91,T100,114;T73,T16,943;T55,T13,540;T1,T13,851;T69,T100,164;T58,T23,13;T76,T19,597;T35,T90,466;T69,T99,721;T20,T9,958;T96,T88,361;T44,T57,43;T17,T59,403;T21,T23,537;T33,T9,833;T45,T72,248;T83,T69,381;T19,T96,800;T48,T92,618;T5,T13,680;T48,T38,957;T24,T46,998;T63,T25,260;T87,T62,538;T24,T5,118;T11,T64,599;T11,T34,49;T73,T62,502;T50,T31,441;T47,T60,582;T20,T37,273;T53,T63,718;T5,T98,601;T4,T21,445;T26,T39,8;T90,T91,280;T1,T94,971;T73,T95,305;T70,T73,819;T69,T68,230;T96,T61,688;T26,T43,73;T90,T30,580;T36,T45,665;T79,T19,191;T34,T3,501;T3,T84,121;T92,T65,495;T90,T68,815;T82,T96,337;T11,T26,950;T48,T47,960;T21,T95,402;T46,T84,579;T66,T23,719;T70,T57,226;T40,T30,279;T16,T43,669;T13,T93,611;T36,T1,728;T79,T52,79;T98,T31,665;T29,T25,527;T30,T96,631;T78,T63,486;T93,T89,964;T61,T50,777;T87,T84,67;T94,T90,735;T70,T45,823;T72,T80,685;T37,T53,436;T54,T88,599;T3,T33,66;T27,T60,54;T55,T12,401;T89,T99,378;T18,T26,935;T14,T33,354;T67,T71,460;T77,T29,260;T29,T31,8;T71,T11,976;T41,T100,310;T59,T13,265;T94,T50,528;T41,T63,920;T2,T18,754;T100,T21,188;T79,T81,301;T93,T90,131;T40,T35,22;T64,T42,305;T56,T97,282;T65,T3,987;T95,T79,808;T94,T14,825;T34,T65,562;T43,T35,339;T46,T13,712;T14,T90,641;T65,T97,856;T81,T60,543;T70,T26,127;T90,T53,578;T81,T48,182;T32,T97,326;T84,T4,218;T46,T82,712;T45,T68,749;T29,T30,572;T97,T71,361;T39,T42,200;T28,T55,242;T14,T20,176;T49,T5,577;T96,T12,685;T100,T99,209;T33,T20,565;T28,T15,207;T92,T14,329;T3,T83,619;T19,T35,251;T96,T51,197;T14,T24,204;T42,T8,927;T56,T90,104;T61,T76,37;T72,T34,414;T40,T66,447;T24,T58,737;T12,T10,26;T25,T54,563;T34,T63,542;T78,T86,972;T33,T51,53;T32,T58,8;T21,T13,552;T77,T81,550;T56,T57,13;T90,T80,975;T22,T28,813;T31,T86,801;T11,T77,670;T68,T41,924;T36,T22,70;T78,T95,766;T40,T42,649;T86,T76,144;T3,T57,826;T6,T65,996;T42,T56,408;T86,T32,237;T94,T89,793;T23,T61,312;T63,T61,475;T47,T26,24;T85,T26,2;T57,T16,34;T99,T26,155;T29,T22,990;T2,T20,474;T35,T70,99;T28,T60,284;T68,T26,914;T76,T81,291;T42,T18,879;T15,T44,913;T6,T26,7;T43,T2,988;T71,T20,547;T86,T2,792;T20,T6,935;T8,T40,511;T9,T46,331;T28,T91,740;T83,T56,446;T28,T50,593;T81,T92,898;T44,T79,505;T67,T2,164;T29,T6,756;T49,T50,645;T42,T43,619;T45,T38,265;T74,T13,996;T74,T80,769;T3,T56,684;T25,T80,684;T80,T95,448;T78,T39,472;T61,T93,527;T30,T45,965;T19,T86,717;T12,T48,433;T84,T35,134;T20,T96,980;T72,T63,719;T95,T68,48;T54,T32,8;T66,T61,522;T50,T81,709;T52,T97,467;T27,T58,60;T47,T88,116;T58,T38,353;T87,T34,307;T58,T22,639;T80,T61,770;T17,T83,518;T97,T81,397;T17,T80,647;T22,T91,729;T35,T36,783;T25,T69,967;T45,T14,196;T42,T75,785;T70,T90,392;T34,T45,39;T61,T70,376;T12,T82,953;"
        } ;
    @Test
    public void test1() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[0]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (1), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (1), isSubSet(answer, tests[0]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[0] + " in test case " + 1, msts[0], mst);
    }


    @Test
    public void test2() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[1]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (2), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (2), isSubSet(answer, tests[1]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[1] + " in test case " + 2, msts[1], mst);
    }

    @Test
    public void test3() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[2]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (3), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (3), isSubSet(answer, tests[2]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[2] + " in test case " + 3, msts[2], mst);
    }

    @Test
    public void test4() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[3]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (4), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (4), isSubSet(answer, tests[3]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[3] + " in test case " + 4, msts[3], mst);
    }

    @Test
    public void test5() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[4]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (5), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (5), isSubSet(answer, tests[4]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[4] + " in test case " + 5, msts[4], mst);
    }

    @Test
    public void test6() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[5]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (6), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (6), isSubSet(answer, tests[5]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[5] + " in test case " + 6, msts[5], mst);
    }

    @Test
    public void test7() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[6]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (7), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (7), isSubSet(answer, tests[6]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[6] + " in test case " + 7, msts[6], mst);
    }

    @Test
    public void test8() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[7]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (8), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (8), isSubSet(answer, tests[7]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[7] + " in test case " + 0, msts[7], mst);
    }

    @Test
    public void test9() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[8]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (9), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (9), isSubSet(answer, tests[8]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[8] + " in test case " + 9, msts[8], mst);
    }

    @Test
    public void test10() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[9]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (10), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (10), isSubSet(answer, tests[9]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[9] + " in test case " + 10, msts[9], mst);
    }

    @Test
    public void test11() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[10]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (11), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (11), isSubSet(answer, tests[10]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[10] + " in test case " + 11, msts[10], mst);
    }

    @Test
    public void test12() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[11]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (12), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (12), isSubSet(answer, tests[11]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[11] + " in test case " + 12, msts[11], mst);
    }

    @Test
    public void test13() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[12]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (13), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (13), isSubSet(answer, tests[12]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[12] + " in test case " + 13, msts[12], mst);
    }

    @Test
    public void test14() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[13]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (14), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (14), isSubSet(answer, tests[13]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[13] + " in test case " + 14, msts[13], mst);
    }

    @Test
    public void test15() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[14]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (15), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (15), isSubSet(answer, tests[14]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[14] + " in test case " + 15, msts[14], mst);
    }

    @Test
    public void test16() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[15]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (16), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (16), isSubSet(answer, tests[15]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[15] + " in test case " + 16, msts[15], mst);
    }

    @Test
    public void test17() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[16]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (17), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (17), isSubSet(answer, tests[16]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[16] + " in test case " + 17, msts[16], mst);
    }

    @Test
    public void test18() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[17]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (18), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (18), isSubSet(answer, tests[17]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[17] + " in test case " + 18, msts[17], mst);
    }

    @Test
    public void test19() throws Exception {
        String answer = ElectricNetwork.minimumConnections(tests[18]);
        assertTrue("The output format is incorrect", checkInput(answer));
        String[] edges = answer.split(";");
        int n = getVerticesCount(edges);
        ArrayList<int[]>[] adjL = parseGraph(edges, n);
        assertTrue("The output is not a spanning tree in test case " + (19), isSpanningTree(adjL, n, edges.length - 1));
        assertTrue("The output contains edge(s) that doesn't exist in the input in test case " + (19), isSubSet(answer, tests[18]));
        int mst = Integer.parseInt(edges[edges.length - 1]);
        int edgesCost = getCost(adjL, n);
        assertEquals("the total cost of the output edges is not equal to the output total weight",edgesCost,mst);
        assertEquals("the minimal overall cost should be " + msts[18] + " in test case " + 19, msts[18], mst);
    }

    private int getCost(ArrayList<int[]>[] adjL, int n) {
        boolean[] vis = new boolean[n];
        return getCost(0, adjL, vis);
    }

    private int getCost(int u, ArrayList<int[]>[] adjL, boolean[] vis) {
        int answer = 0;
        vis[u] = true;
        for (int[] e : adjL[u]) {
            if (vis[e[0]])
                continue;
            answer += e[1] + getCost(e[0], adjL, vis);
        }
        return answer;
    }

    private boolean checkInput(String answer) {
        try {
            StringTokenizer st = new StringTokenizer(answer, ";");
            boolean edges = true;
            int c = 0;
            while (st.countTokens() > 1) {
                String edge = st.nextToken();
                String[] temp = edge.split(",");
                for (int i = 0; i < 2; i++) {
                    edges &= temp[i].charAt(0) == 'T';
                    edges &= isInteger(temp[i].substring(1));
                }
                edges &= isInteger(temp[2]);
                c++;
            }
            return c > 0 && edges && isInteger(st.nextToken());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isSubSet(String answer, String test) {
        String[] answerEdges = answer.split(";");
        String[] testEdges = test.split(";");
        int answerVertices = getVerticesCount(answerEdges);
        int testVertices = getVerticesCount(testEdges);
        ArrayList<int[]>[] answerAdjL = parseGraph(answerEdges, answerVertices);
        ArrayList<int[]>[] testAdjL = parseGraph(testEdges, testVertices);
        for (int i = 0; i < answerVertices; i++)
            for (int[] edge : answerAdjL[i])
                if (!contains(testAdjL[i], edge))
                    return false;
        return true;
    }

    private boolean contains(ArrayList<int[]> ints, int[] edge) {
        for (int[] x : ints)
            if (Arrays.equals(x, edge))
                return true;
        return false;
    }

    private boolean isSpanningTree(ArrayList<int[]>[] adjL, int n, int m) {
        boolean[] vis = new boolean[n];
        int countVerticesFromSource = dfs(0, adjL, vis);
        if (countVerticesFromSource != n)
            return false;
        return m == n - 1;
    }

    private int dfs(int u, ArrayList<int[]>[] adjL, boolean[] vis) {
        int answer = 1;
        vis[u] = true;
        for (int[] e : adjL[u]) {
            if (vis[e[0]])
                continue;
            answer += dfs(e[0], adjL, vis);
        }
        return answer;
    }

    private ArrayList<int[]>[] parseGraph(String[] edges, int n) {
        ArrayList<int[]>[] adjL = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjL[i] = new ArrayList<>();
        }
        int m = edges.length;
        for (String edge : edges) {
            String[] temp = edge.split(",");
            if (temp[0].charAt(0) != 'T')
                break;
            int u = Integer.parseInt(temp[0].substring(1)) - 1;
            int v = Integer.parseInt(temp[1].substring(1)) - 1;
            int cost = Integer.parseInt(temp[2]);
            adjL[u].add(new int[]{v, cost});
            adjL[v].add(new int[]{u, cost});
        }
        return adjL;
    }

    private int getVerticesCount(String[] edges) {
        int max = 1;
        for (String edge : edges) {
            String[] temp = edge.split(",");
            if (temp[0].charAt(0) != 'T')
                break;
            int u = Integer.parseInt(temp[0].substring(1));
            int v = Integer.parseInt(temp[1].substring(1));
            max = Math.max(u, max);
            max = Math.max(v, max);
        }
        return max;
    }
}
