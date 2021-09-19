/**
 * @Author: Yan Jingcun
 * @Date: 2021/9/14
 * @Description:
 * @Version: 1.0
 */

package com.jancoyan.jancoblog;

import com.jancoyan.jancoblog.JancoBlogApplicationTests;
import com.jancoyan.jancoblog.pojo.Article;
import com.jancoyan.jancoblog.pojo.Comment;
import com.jancoyan.jancoblog.pojo.User;
import com.jancoyan.jancoblog.utils.MD5Util;
import org.junit.jupiter.api.Test;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.Random;

public class Injection extends JancoBlogApplicationTests {

    // 用于随机生成文本的字符串
    // 1858
    String text =
            "这里是汉代的豫章郡城，如今是洪州的都督府，天上的方位属于翼，轸两星宿的分野，地上的位置连结着\n" +
                    "衡山和庐山。以三江为衣襟，以五湖为衣带、控制着楚地，连接着闽越。物类的精华，是上天的珍宝，宝剑的光芒直冲上牛、斗二星的区间。人中有英杰，因大地有灵气，陈蕃专为徐孺设下几榻。雄伟的洪州城，房屋像雾一般罗列，英俊的人才，像繁星一样地活跃。城池座落在夷夏交界的要害之地，主人与宾客，集中了东南地区的英俊之才。都督阎公，享有崇高的名望，远道来到洪州坐镇，宇文州牧，是美德的楷模，\n" +
                    "赴任途中在此暂留。正逢十日休假的日子，杰出的友人云集；高贵的宾客，也都不远千里来到这里聚会。文坛领袖孟学士，其文采像腾起的蛟龙、飞舞的彩凤，王将军的武库里，藏有像紫电、青霜一样锋利的宝剑。由于父亲在交趾做县令，我在探亲途中经过这个著名的地方。我年幼无知，竟有幸亲身参加了这次盛大的宴会。时当九月，秋高气爽。积水消尽，潭水清澈，天空凝结着淡淡的云烟，暮霭中山峦呈现一片紫色。在高高的山路上驾着马车，在崇山峻岭中访求风景。来到昔日帝子的长洲，发现了滕王所修的滕王阁。这里山峦重叠，青翠的山峰耸入云霄。凌空的楼阁，红色的阁道犹如飞翔在天空，从阁上看不到地面。白鹤，野鸭停息的小洲，极尽岛屿的纡曲回环之势，雅浩的宫殿，跟起伏的山峦配合有致。推开雕花的阁门，俯视彩饰的屋脊，山峰平原尽收眼底，湖川曲折令人惊讶。遍地是里巷宅舍，许多钟鸣鼎食的富贵人家。舸舰塞满了渡口，尽是雕上了青雀黄龙花纹的大船。正值雨过天晴，虹消云散，阳光朗煦，落霞与孤雁一起飞翔，秋天的江水和辽阔的天空连成一片，浑然一色。傍晚渔舟中传出的歌声，响彻彭蠡湖滨，雁群感到寒意而发出的惊叫，鸣声到衡阳之浦为止放眼远望，胸襟刚感到舒畅，超逸的兴致立即兴起，排箫的音响引来的徐徐清风，柔缓的歌声吸引住飘动的白云。像睢园竹林的聚会，这里善饮的人，酒量超过彭泽县令陶渊明，像邺水赞咏莲花，这里诗人的文采，胜过临川内史谢灵运。（音乐与饮食，文章和言语）这四种美好的事物都已经齐备，（贤主、嘉宾）这两个难得的条件也凑合在一起了，向天空中极目远眺，在假日里尽情欢娱。苍天高远，大地寥廓，令人感到宇宙的无穷无尽。欢乐逝去，悲哀袭来，我知道了事物的兴衰成败是有定数的。西望长安，东指吴会，南方的陆地已到尽头，大海深不可测，北方的北斗星多么遥远，天柱高不可攀。关山重重难以越过，有谁同情不得志的人？萍水偶尔相逢，大家都是异乡之客．怀念\n" +
                    "着君王的宫门，但却不被召见，什么时候才能够去侍奉君王呢？呵！各人的时机不同，人生的命运多有不顺。冯唐容易衰老，李广难得封侯。使贾谊遭受委屈，贬于长沙，并不是没有圣明的君主，使梁鸿逃匿到齐鲁海滨，难道不是政治昌明的时代？只不过由于君子能了解时机，通达的人知道自己的命运罢了。年纪虽然老了，但志气应当更加旺盛，怎能在白头时改变心情？境遇虽然困苦，但节操应当更加坚定，决不能抛弃自己的凌云壮志。即使喝了贪泉的水，仍觉着神清气爽，即使身处于干涸的主辙中，也是欢乐无比。北海虽然十分遥远，雅浩的宫殿可以联通；早晨虽然已经过去，而珍惜黄昏却为时不晚。孟尝君心地高洁，但白白地怀抱着报国的热情；阮籍为人放纵不羁，我们怎能学他那种穷途的哭泣！我王勃，地位卑微，只是一个书生。却无处去请缨杀敌。虽然和终军一样年已二十一，也有投笔从戎的志向。我羡慕宗悫那种“乘长风破万里浪”的英雄气概，如今我抛弃了一生的功名，到万里之外朝夕侍奉父亲。虽然称不上谢家的“宝树”，但是能和贤德之士相交往。不久我将见到父亲，聆听他的教诲；今天我侥幸地奉陪各位长者，高兴地登上龙门。假如碰不上杨得意那样引荐的人，就只有抚拍着自己的文章而自我叹惜。既然已经遇到了钟子期，就弹奏一曲《流水》又有什么羞愧呢？呵！名胜之地不能常存，盛大的宴会难以再逢，兰亭宴集已为陈迹，石崇的梓泽也变成了废墟。让我临别时作了这一篇序文，承蒙这个宴会的恩赐，至于登高作赋，这只有指望在座诸公了。我只是冒昧地尽我微薄的心意，作了短短的引言。在座诸位都按各自分到的韵字赋诗，我已写成了四韵八句。在座诸位施展潘岳，施展陆机一样的才笔，各自谱写瑰丽的诗篇吧：巍峨高耸的滕王阁俯临着江心的沙洲，想当初佩玉、鸾铃鸣响的豪华歌舞已经停止了。早晨，画栋飞上了南浦的云，黄昏，珠帘卷入了西山的雨。悠闲的彩云影子倒映在江水中，整天悠悠然地漂浮着，时光易逝，人事变迁，\n" +
                    "不知已经度过几个春秋。昔日游赏于高阁中的滕王如今已不知哪里去了？只有那栏杆外的滔滔江水空自向远方奔流。";


    // 870
    String comment = "豫章故郡，洪都新府。星分翼轸，地接衡庐。襟三江而带五湖，控蛮荆而引瓯越。物华天宝，龙光射牛斗之墟；人杰地灵，徐孺下陈蕃之榻。雄州雾列，俊采星驰。台隍枕夷夏之交，宾主尽东南之美。都督阎公之雅望，棨戟遥临；宇文新州之懿范，襜帷暂驻。十旬休假，胜友如云；千里逢迎，高朋满座。腾蛟起凤，孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；童子何知，躬逢胜饯。\n" +
            "时维九月，序属三秋。潦水尽而寒潭清，烟光凝而暮山紫。俨骖騑于上路，访风景于崇阿；临帝子之长洲，得天人之旧馆。层峦耸翠，上出重霄；飞阁流丹，下临无地。鹤汀凫渚，穷岛屿之萦回；桂殿兰宫，即冈峦之体势。\n" +
            "披绣闼，俯雕甍，山原旷其盈视，川泽纡其骇瞩。闾阎扑地，钟鸣鼎食之家；舸舰弥津，青雀黄龙之舳。云销雨霁，彩彻区明。落霞与孤鹜齐飞，秋水共长天一色。渔舟唱晚，响穷彭蠡之滨；雁阵惊寒，声断衡阳之浦。\n" +
            "遥襟甫畅，逸兴遄飞。爽籁发而清风生，纤歌凝而白云遏。睢园绿竹，气凌彭泽之樽；邺水朱华，光照临川之笔。四美具，二难并。穷睇眄于中天，极娱游于暇日。天高地迥，觉宇宙之无穷；兴尽悲来，识盈虚之有数。望长安于日下，目吴会于云间。地势极而南溟深，天柱高而北辰远。关山难越，谁悲失路之人？萍水相逢，尽是他乡之客。怀帝阍而不见，奉宣室以何年？\n" +
            "嗟乎！时运不齐，命途多舛。冯唐易老，李广难封。屈贾谊于长沙，非无圣主；窜梁鸿于海曲，岂乏明时？所赖君子见机，达人知命。老当益壮，宁移白首之心？穷且益坚，不坠青云之志。酌贪泉而觉爽，处涸辙以犹欢。北海虽赊，扶摇可接；东隅已逝，桑榆非晚。孟尝高洁，空余报国之情；阮籍猖狂，岂效穷途之哭！\n" +
            "勃，三尺微命，一介书生。无路请缨，等终军之弱冠；有怀投笔，慕宗悫之长风。舍簪笏于百龄，奉晨昏于万里。非谢家之宝树，接孟氏之芳邻。他日趋庭，叨陪鲤对；今兹捧袂，喜托龙门。杨意不逢，抚凌云而自惜；钟期既遇，奏流水以何惭？\n" +
            "呜乎！胜地不常，盛筵难再；兰亭已矣，梓泽丘墟。临别赠言，幸承恩于伟饯；登高作赋，是所望于群公。敢竭鄙怀，恭疏短引；一言均赋，四韵俱成。请洒潘江，各倾陆海云尔：";

    String titleStr = "豫章故郡洪都新府星分翼轸地接衡庐襟三";

    String password = "1234567890";

    @Test
    public void commentDataInjection(){
        Random r = new Random();
        Comment comment = new Comment();

        for (int i = 0; i < 1000; i++) {

            comment.setCommentId(null);
            comment.setCommentArticleId(String.valueOf(1 + r.nextInt(50)));
            comment.setCommentDate(new Date());
            comment.setCommentContent(this.comment.substring(0, r.nextInt(100)));
            comment.setCommentAuthorId(10400 + r.nextInt(400));
            comment.setCommentLikeCount(r.nextInt(100));
            comment.insert();
            System.out.println(i);
        }
    }


    public void articleDataInjection(){
//         随机添加1000条数据
        Article article = new Article();
        Random r = new Random();
        for (int i = 1; i < 800; i++){
            article.setArticleId(String.valueOf(i));
            article.setArticleTitle(titleStr.substring(r.nextInt(15), 17));
            article.setArticleAuthor(10800);
            article.setArticleHtml(text.substring(r.nextInt(400), 1458 + r.nextInt(400)));
            article.setArticleMd(text.substring(r.nextInt(400), 1458 + r.nextInt(400)));
            article.setArticleSummary(text.substring(r.nextInt(100), 200));
            article.setArticleType(1 + r.nextInt(12));
            article.setArticleCommentCount(r.nextInt(100));
            article.setArticleLikeCount(r.nextInt(100));
            article.setArticleViewCount(r.nextInt(100));
            article.setArticleEditTime(new Date());
            article.setArticlePostTime(new Date());
            article.insert();
            System.out.println(i);
        }
    }


    public void userDataInjection(){
        Random r = new Random();
        User user = new User();
        for (int i = 10001; i < 10400; i++){
            user.setUserId(i);
            user.setUserName("user-" + i);
            user.setUserPassword("123456");
            user.insert();
            System.out.println(i);
        }
    }

    public void userInjection(){
        User user = new User();
        user.setUserIp("127.0.0.1").
                setUserName("admin")
                .setUserPassword(MD5Util.getMD5("333"))
                .setUserEmail("301203812@qq.com")
                .setUserRole(0)
                .setUserSex(1)
                .setUserCreateDate(new Date())
                .setUserLastLoginDate(new Date());
        user.insert();
    }

}
