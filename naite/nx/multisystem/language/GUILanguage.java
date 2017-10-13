package nx.multisystem.language;

import java.util.Arrays;
import java.util.List;

public class GUILanguage{

	public static final String CORE_DISPLAY_NAME = "§2客戶端";

	public static final String ITEM_DATA_NAME = "§6\u25c8 數據 \u25c8";

	public static final List<String> ITEM_DATA_LORE =
			Arrays.asList("§7查詢自己目前的基礎數值",
					"§7可從這些數據中了解自己的潛在能力",
					"§7並能詳細清楚自我數值的優缺點",
					"",
					"§a\u27a0 點擊查看");

	public static final String ITEM_PROPERTY_NAME = "§6\u25c8 屬性 \u25c8";

	public static final List<String> ITEM_PROPERTY_LORE =
			Arrays.asList("§7可從中了解屬性的用途",
					"§7並且選擇自行所需的屬性",
					"§7將選擇的屬性提升自己的基礎數值",
					"§7讓自己的實力強化",
					"",
					"§a\u27a0 點擊查看");

	public static final String DATA_DISPLAY_NAME = "§2玩家數據";

	public static final String ITEM_LEVEL_NAME = "§6等級";

	public static final List<String> ITEM_LEVEL_LORE =
			Arrays.asList("",
					"§7升級等級讓您獲得屬性點數",
					"§7並且可以使用屬性點數提升基礎素質能力",
					"",
					"§f當前等級: §a%lvl%");

	public static final String ITEM_EXP_NAME = "§6經驗";

	public static final List<String> ITEM_EXP_LORE =
			Arrays.asList("",
					"§7獲得經驗可提升您的等級",
					"§7經驗可從打怪、任務等...嘗試獲取",
					"§7這是一種累積您的遊玩長久性",
					"",
					"§f當前經驗: §a%exp%",
					"§f所需經驗: §a%needexp%",
					"§f最大經驗: §a%maxexp%");

	public static final String ITEM_HEALTH_NAME = "§e生命";

	public static final List<String> ITEM_HEALTH_LORE =
			Arrays.asList("§0 ",
					"§7提升屬性 §d\u271a §7可讓您的生命值提升",
					"§7提升屬性 §2\u2724 §7可讓您的生命恢復提升",
					"§7受到傷害時會減少您的生命值",
					"§7每 1 分鐘恢復一次生命值",
					"",
					"§f當前生命: §a%hp%",
					"§f生命恢復: §a%rehp%",
					"§f最大生命: §a%maxhp%");

	public static final String ITEM_MENTALITY_NAME = "§e精力";

	public static final List<String> ITEM_MENTALITY_LORE =
			Arrays.asList("",
					"§7提升屬性 §d\u271a §7可讓您的精力值提升",
					"§7提升屬性 §2\u2724 §7可讓您的精力恢復提升",
					"§7當進入遊戲後會開始減少您的精力值",
					"§7每 1 小時恢復一次精力值",
					"",
					"§f當前精力: §a%men%",
					"§f精力恢復: §a%remen%",
					"§f最大精力: §a%maxmen%");

	public static final String ITEM_VITALITY_NAME = "§e耐力";

	public static final List<String> ITEM_VITALITY_LORE =
			Arrays.asList("",
					"§7提升屬性 §d\u271a §7可讓您的耐力值提升",
					"§7提升屬性 §2\u2724 §7可讓您的耐力恢復提升",
					"§7當移動時會開始減少您的耐力值",
					"§7每 45 分鐘恢復一次耐力值",
					"",
					"§f當前耐力: §a%vit%",
					"§f耐力恢復: §a%revit%",
					"§f最大耐力: §a%maxvit%");

	public static final String ITEM_ATK_NAME = "§d物理攻擊";

	public static final List<String> ITEM_ATK_LORE =
			Arrays.asList("",
					"§7提升屬性 §c\u2738 §7可讓您的物理攻擊提升",
					"§7也可以從其他物品裝備取得提升能力",
					"§7每攻擊一次可造成基礎傷害",
					"",
					"§f當前物理攻擊: §a%atk%");

	public static final String ITEM_DEF_NAME = "§d物理防禦";

	public static final List<String> ITEM_DEF_LORE =
			Arrays.asList("",
					"§7提升屬性 §e\u262c §7可讓您的物理防禦提升",
					"§7也可以從其他物品裝備取得提升能力",
					"§7每受到一次傷害可使防禦抵抗",
					"",
					"§f當前物理防禦: §a%def%");

	public static final String ITEM_AAR_NAME = "§d物理閃避率";

	public static final List<String> ITEM_AAR_LORE =
			Arrays.asList("§0 ",
					"§7提升屬性 §e\u262c §7可讓您的物理閃避率提升",
					"§7也可以從其他物品裝備取得提升能力",
					"§7每受到一次攻擊可使閃避率觸發一次迴避",
					"§0 ",
					"§f當前物理閃避率: §a%aar%");

	public static final String ITEM_AKB_NAME = "§d物理爆擊率";

	public static final List<String> ITEM_AKB_LORE =
			Arrays.asList("",
					"§7提升屬性 §4\u2623 §7可讓您的物理爆擊率提升",
					"§7也可以從其他物品裝備取得提升能力",
					"§7每攻擊一次可使爆擊率觸發一次雙倍傷害",
					"",
					"§f當前物理爆擊率: §a%akb%");

	public static final String ITEM_AHIT_NAME = "§d物理命中率";

	public static final List<String> ITEM_AHIT_LORE =
			Arrays.asList("",
					"§7提升屬性 §4\u2623 §7可讓您的物理命中率提升",
					"§7也可以從其他物品裝備取得提升能力",
					"§7每攻擊一次可使命中率觸發是否造成傷害",
					"§0 ",
					"§f當前物理命中率: §a%ahit%");

	public static final String PROPERTY_DISPLAY_NAME = "§2玩家屬性  您剩下 §c§l%point% §2點";

	public static final String ITEM_STR_NAME = "§6力量屬性 §f- §c\u2738";

	public static final List<String> ITEM_STR_LORE =
			Arrays.asList("",
					"§7提升力量可以使您的物理攻擊上升",
					"§7增加對於玩家或怪物的基礎傷害",
					"§7目前 §c\u2738 §7擁有 §f%str% §7點",
					"",
					"§7適合於擅長使用刀劍等...的遊戲玩家",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_INT_NAME = "§6智力屬性 §f- §b\u274b";

	public static final List<String> ITEM_INT_LORE =
			Arrays.asList("",
					"§7提升智力可以使您的魔法攻擊上升",
					"§7增加對於施展魔法技能的基礎傷害",
					"§7目前 §b\u274b §7擁有 §f%int% §7點",
					"",
					"§7適合於擅長使用棍杖等...的遊戲玩家",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_AGI_NAME = "§6敏捷屬性 §f- §e\u262c";

	public static final List<String> ITEM_AGI_LORE =
			Arrays.asList("",
					"§7提升敏捷可以使您的物理與魔法的閃避率與防禦上升",
					"§7增加對於受到傷害時的抵抗力與迴避",
					"§7目前 §e\u262c §7擁有 §f%agi% §7點",
					"",
					"§7適合於擅長使用盾甲或坦克類型等...的遊戲玩家",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_LUK_NAME = "§6幸運屬性 §f- §4\u2623";

	public static final List<String> ITEM_LUK_LORE =
			Arrays.asList("",
					"§7提升幸運可以使您的物理與魔法的爆擊率與命中率上升",
					"§7增加對於目標受到傷害時的而外傷害與命中機率",
					"§7目前 §4\u2623 §7擁有 §f%luk% §7點",
					"",
					"§7適合於擅長使用弓弩或匕首等...的遊戲玩家",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_CON_NAME = "§6體質屬性 §f- §d\u271a";

	public static final List<String> ITEM_CON_LORE =
			Arrays.asList("",
					"§7提升體質可以使您的血量、魔力、精力與耐力上升",
					"§7依照自己的需求而增加對於體質的要點",
					"§7目前 §d\u271a §7擁有 §f%con% §7點",
					"",
					"§7適合於所有生存與冒險的玩家使用",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_WIS_NAME = "§6感知屬性 §f- §2\u2724";

	public static final List<String> ITEM_WIS_LORE =
			Arrays.asList("",
					"§7提升感知可以使您的血量、魔力、精力與耐力的恢復值上升",
					"§7依照自己的需求而增加對於感知的要點",
					"§7目前 §2\u2724 §7擁有 §f%wis% §7點",
					"",
					"§7適合於補師、法師、巫師等...使用者的遊戲玩家",
					"",
					"§a\u27a0 點擊該項目將可提升");

	public static final String ITEM_BACK_NAME = "§c返回首頁";

	public static final List<String> ITEM_BACK_LORE =
			Arrays.asList("",
					"§7回到屬性系統介面",
					"",
					"§a➠ 點擊返回");

}
