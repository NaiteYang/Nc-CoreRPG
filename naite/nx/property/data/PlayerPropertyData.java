package nx.property.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import nx.property.core.Core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerPropertyData{

	private static String title = "§6[NcProperty] ";

	// 客戶端數值

	Player player;  // 玩家

	private int point = 0;  // 屬性點數
	private int level = 1;  // 等級
	private int exp = 0;  // 經驗

	//狀態值
	private int mana = 0;        // 魔力
	private int maxMana = 0;  // 最大魔力  (屬性制 - 體質)
	private int restoreMana = 0;  // 魔力恢復  (屬性制 - 感知)

	private int health = 0;      // 血量
	private int maxHealth = 0;  // 最大血量  (屬性制 - 體質)
	private int restoreHealth = 0;  // 血量恢復  (屬性制 - 感知)

	private int mentality = 0;        // 精力
	private int maxMentality = 0;    // 最大精力  (屬性制 - 體質)
	private int restoreMentality = 0;  // 精力恢復  (屬性制 - 感知)

	private int vitality = 0;        // 耐力
	private int maxVitality = 0;    // 最大耐力  (屬性制 - 體質)
	private int restoreVitality = 0;  // 耐力恢復  (屬性制 - 感知)

	// 能力值
	private int atk = 0;        // 物理攻擊		(屬性制 - 力量)
	private int mag = 0;        // 魔法攻擊		(屬性制 - 智力)
	private int def = 0;        // 物理防禦		(屬性制 - 敏捷)
	private int res = 0;        // 魔法防禦		(屬性制 - 敏捷)
	private double aar = 0;        // 物理閃避率	(屬性制 - 敏捷)
	private double sar = 0;        // 魔法閃避率	(屬性制 - 敏捷)
	private double akb = 0;        // 物理爆擊率	(屬性制 - 幸運)
	private double skb = 0;        // 魔法爆擊率	(屬性制 - 幸運)
	private double ahit = 0;    // 物理命中率	(屬性制 - 幸運)
	private double shit = 0;    // 魔法命中率	(屬性制 - 幸運)

	// 屬性數值
	private int str = 0;        // 力量屬性  (物理攻擊)
	private int inte = 0;        // 智力屬性  (魔法攻擊)
	private int agi = 0;        // 敏捷屬性  (物理閃避率、魔法閃避率、物理防禦、魔法防禦)
	private int luk = 0;        // 幸運屬性  (物理爆擊率、魔法爆擊率、物理命中率、魔法命中率)
	private int con = 0;        // 體質屬性  (血量、魔力、精力、耐力)
	private int wis = 0;        // 感知屬性  (血量恢復、魔力恢復、精力恢復、耐力恢復)

	//檔案
	private File file = null;
	private YamlConfiguration yaml = null;

	private static HashMap<Player, PlayerPropertyData> playerDatas = new HashMap<>();

	// 玩家資料
	public PlayerPropertyData(Player player){
		this.player = player;
		player.setHealthScaled(true);
		loadFile();

		playerDatas.put(player, this);
	}

	public static PlayerPropertyData getPlayerData(UUID uuid){
		return getPlayerData(Bukkit.getPlayer(uuid));
	}

	public static PlayerPropertyData getPlayerData(Player player){
		if(playerDatas.containsKey(player)){
			return playerDatas.get(player);
		}
		else{
			return new PlayerPropertyData(player);
		}
	}

	public static void removePlayerData(UUID uuid){
		playerDatas.remove(Bukkit.getPlayer(uuid));
	}

	public static void removePlayerData(Player player){
		playerDatas.remove(player);
	}

	//儲存狀態值
	public void saveStatusData(){
		yaml.set("mana", getMana());
		yaml.set("health", getHealth());
		yaml.set("mentality", getMentality());
		yaml.set("vitality", getVitality());
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	//檔案讀取
	private void loadFile(){
		file = new File(Core.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");

		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		yaml = YamlConfiguration.loadConfiguration(file);

		//讀取資料

		//基本資料
		if(yaml.getString("point", null) == null){
			yaml.set("point", 0);
		}
		point = yaml.getInt("point");

		if(yaml.getString("level", null) == null){
			yaml.set("level", 1);
		}
		level = yaml.getInt("level");

		if(yaml.getString("exp", null) == null){
			yaml.set("exp", 0);
		}
		exp = yaml.getInt("exp");

		//屬性
		if(yaml.getString("str", null) == null){
			yaml.set("str", 0);
		}
		str = yaml.getInt("str") > PropertySettings.getMaxStr() ? PropertySettings.getMaxStr() : yaml.getInt("str");
		computeStr();

		if(yaml.getString("int", null) == null){
			yaml.set("int", 0);
		}
		inte = yaml.getInt("int") > PropertySettings.getMaxInt() ? PropertySettings.getMaxInt() : yaml.getInt("int");
		computeInt();

		if(yaml.getString("agi", null) == null){
			yaml.set("agi", 0);
		}
		agi = yaml.getInt("agi") > PropertySettings.getMaxAgi() ? PropertySettings.getMaxAgi() : yaml.getInt("agi");
		computeAgi();

		if(yaml.getString("luk", null) == null){
			yaml.set("luk", 0);
		}
		luk = yaml.getInt("luk") > PropertySettings.getMaxLuk() ? PropertySettings.getMaxLuk() : yaml.getInt("luk");
		computeLuk();

		if(yaml.getString("con", null) == null){
			yaml.set("con", 0);
		}
		con = yaml.getInt("con");
		computeCon();

		if(yaml.getString("wis", null) == null){
			yaml.set("wis", 0);
		}
		wis = yaml.getInt("wis");
		computeWis();

		//狀態值
		if(yaml.getString("mana", null) == null){
			yaml.set("mana", getMaxMana());
		}
		mana = yaml.getInt("mana") > getMaxMana() ? getMaxMana() : yaml.getInt("mana");

		if(yaml.getString("health", null) == null){
			yaml.set("health", getMaxHealth());
		}
		health = yaml.getInt("health") > getMaxHealth() ? getMaxHealth() : yaml.getInt("health");
		player.setHealthScale(health);

		if(yaml.getString("mentality", null) == null){
			yaml.set("mentality", getMaxMentality());
		}
		mentality = yaml.getInt("mentality") > getMaxMentality() ? getMaxMentality() : yaml.getInt("mentality");

		if(yaml.getString("vitality", null) == null){
			yaml.set("vitality", getMaxVitality());
		}
		vitality = yaml.getInt("vitality") > getMaxVitality() ? getMaxVitality() : yaml.getInt("vitality");

		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	//計算屬性相關的能力值與狀態值

	private void computeStr(){
		atk = PropertySettings.getDefaultAtk() + getStr(); //物理攻擊 = 預設 + 力量*1
	}

	private void computeInt(){
		mag = PropertySettings.getDefaultMag() + getInt() * 2; //魔法攻擊 = 預設 + 智力*2
	}

	private void computeAgi(){
		def = PropertySettings.getDefaultDef() + getAgi() * 2; //物理防禦 = 預設 + 敏捷*2
		res = PropertySettings.getDefaultRes() + getAgi(); //魔法防禦 = 預設 + 敏捷*1
		aar = PropertySettings.getDefaultAar() + getAgi() * 0.03; //物理閃避 = 預設 + 敏捷*0.03
		sar = PropertySettings.getDefaultSar() + getAgi() * 0.02; //魔法閃避 = 預設 + 敏捷*0.02
	}

	private void computeLuk(){
		akb = PropertySettings.getDefaultAkb() + getLuk() * 0.05; //物理爆擊 = 預設 + 幸運*0.05
		skb = PropertySettings.getDefaultSkb() + getLuk() * 0.03; //魔法爆擊 = 預設 + 幸運*0.03
		ahit = PropertySettings.getDefaultAhit() + getLuk() * 0.02; //物理命中 = 預設 + 幸運*0.02
		shit = PropertySettings.getDefaultShit() + getLuk() * 0.01; //魔法命中 = 預設 + 幸運*0.01
	}

	private void computeCon(){
		maxHealth = PropertySettings.getDefaultMaxHealth() + getCon() * 10; //最大血量 = 預設 + 體質*10
		maxMana = PropertySettings.getDefaultMaxMana() + getCon() * 5; //最大魔力 = 預設 + 體質*5
		maxMentality = PropertySettings.getDefaultMaxMentality() + getCon(); //最大精力 = 預設 + 體質*1
		maxVitality = PropertySettings.getDefaultMaxVitality() + getCon(); //最大耐力 = 預設 + 體質*1
	}

	private void computeWis(){
		restoreHealth = PropertySettings.getDefaultRestoreHealth() + getWis() * 5; //血量恢復 = 預設 + 感知*5
		restoreMana = PropertySettings.getDefaultRestoreMana() + getWis() * 5; //魔力恢復 = 預設 + 感知*5
		restoreMentality = PropertySettings.getDefaultRestoreMentality() + getWis(); //精力恢復 = 預設 + 感知*1
		restoreVitality = PropertySettings.getDefaultRestoreVitality() + getWis(); //耐力恢復 = 預設 + 感知*1
	}

	//設定(並且更新該屬性相關的能力值與變更檔案內容)與取得屬性

	//STR
	public void setStr(int value){
		str = value > PropertySettings.getMaxStr() ? PropertySettings.getMaxStr() : value;
		computeStr();
		yaml.set("str", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getStr(){
		return str;
	}

	public void addStr(int value){
		setStr(getStr() + value);
	}

	//INT
	public void setInt(int value){
		inte = value > PropertySettings.getMaxInt() ? PropertySettings.getMaxInt() : value;
		computeInt();
		yaml.set("int", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getInt(){
		return inte;
	}

	public void addInt(int value){
		setInt(getInt() + value);
	}

	//AGI
	public void setAgi(int value){
		agi = value > PropertySettings.getMaxAgi() ? PropertySettings.getMaxAgi() : value;
		computeAgi();
		yaml.set("agi", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getAgi(){
		return agi;
	}

	public void addAgi(int value){
		setAgi(getAgi() + value);
	}

	//LUK
	public void setLuk(int value){
		luk = value > PropertySettings.getMaxLuk() ? PropertySettings.getMaxLuk() : value;
		computeLuk();
		yaml.set("luk", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getLuk(){
		return luk;
	}

	public void addLuk(int value){
		setLuk(getLuk() + value);
	}

	//CON
	public void setCon(int value){
		con = value > PropertySettings.getMaxCon() ? PropertySettings.getMaxCon() : value;
		computeCon();
		yaml.set("con", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getCon(){
		return con;
	}

	public void addCon(int value){
		setCon(getCon() + value);
	}

	//WIS
	public void setWis(int value){
		wis = value > PropertySettings.getMaxWis() ? PropertySettings.getMaxWis() : value;
		computeWis();
		yaml.set("wis", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getWis(){
		return wis;
	}

	public void addWis(int value){
		setWis(getWis() + value);
	}

	//技能點設定(並且變更檔案內容)與取得

	public void setPoint(int value){
		point = value;

		yaml.set("point", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getPoint(){
		return point;
	}

	public void addPoint(int value){
		setPoint(getPoint() + value);
	}

	// 等級管理

	private void changeMinecraftExp(){ //修改原版經驗條
		//經驗條修改
		player.setLevel(level);

		int max;
		if(level <= 15){
			max = 2 * level + 7;
		}
		else if(level <= 30){
			max = 5 * level - 38;
		}
		else{
			max = 9 * level - 158;
		}

		player.setExp(max * (level / PropertySettings.getMaxExp(level)));
	}

	public void addLevel(int lvl){ //提升等級(並且發送訊息及增加技能點)
		setLevel(getLevel() + lvl); //提升等級
		addPoint(2 * lvl); //point + 2

		player.sendMessage(title + "§a恭喜您升級了!");
		player.sendMessage(title + "§e目前等級是§f" + level + "§e級");
	}

	public void setLevel(int lvl){ //設定等級並變更檔案內容
		level = lvl;
		yaml.set("level", lvl);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getLevel(){
		return level;
	}

	public void addExp(int e){ //增加經驗值(若超過最大經驗會升級)
		setExp(getExp() + e);
	}

	public void setExp(int e){ //設定經驗值(若超過最大經驗會升級)(並變更檔案內容)
		exp = e;

		int upLvl = 0; //提升的等級
		for(; ; ){
			if(exp >= PropertySettings.getMaxExp(level) && level < PropertySettings.getMaxLevel()){ //達到可升級經驗且未達最高等級
				exp -= PropertySettings.getMaxExp(level); //扣除經驗值
				upLvl++;
			}
			else{
				break;
			}
		}
		if(upLvl > 0){
			addLevel(upLvl);
		}

		changeMinecraftExp();

		yaml.set("exp", getExp());
		try{
			yaml.save(file);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public int getExp(){
		return exp;
	}

	//狀態值控制

	//mana
	public int getMana(){
		return mana;
	}

	public int getMaxMana(){
		return maxMana;
	}

	public int getRestoreMana(){
		return restoreMana;
	}

	public void setMana(int value){
		if(value > getMaxMana()){
			mana = getMaxMana();
		}
		else if(value < 0){
			mana = 0;
		}
		else{
			mana = value;
		}
	}

	public void removeMana(int value){
		setMana(getMana() - value);
	}

	public void addMana(int value){
		setMana(getMana() + value);
	}

	//health
	public int getHealth(){
		return health;
	}

	public int getMaxHealth(){
		return maxHealth;
	}

	public int getRestoreHealth(){
		return restoreHealth;
	}

	public void setHealth(int value){
		if(value > getMaxHealth()){
			health = getMaxHealth();
		}
		else if(value < 0){
			health = 0;
		}
		else{
			health = value;
		}
		player.setHealthScale(getHealth());
	}

	public void removeHealth(int value){
		setHealth(getHealth() - value);
	}

	public void addHealth(int value){
		setHealth(getHealth() + value);
	}

	//mentality
	public int getMentality(){
		return mentality;
	}

	public int getMaxMentality(){
		return maxMentality;
	}

	public int getRestoreMentality(){
		return restoreMentality;
	}

	public void setMentality(int value){
		if(value > getMaxMentality()){
			mentality = getMaxMentality();
		}
		else if(value < 0){
			mentality = 0;
		}
		else{
			mentality = value;
		}
	}

	public void removeMentality(int value){
		setMentality(getMentality() - value);
	}

	public void addMentality(int value){
		setMentality(getMentality() + value);
	}

	//vitality
	public int getVitality(){
		return vitality;
	}

	public int getMaxVitality(){
		return maxVitality;
	}

	public int getRestoreVitality(){
		return restoreVitality;
	}

	public void setVitality(int value){
		if(value > getMaxVitality()){
			vitality = getMaxVitality();
		}
		else if(value < 0){
			vitality = 0;
		}
		else{
			vitality = value;
		}
	}

	public void removeVitality(int value){
		setVitality(getVitality() - value);
	}

	public void addVitality(int value){
		setVitality(getVitality() + value);
	}

	//取得能力值

	public int getAtk(){
		return atk;
	}

	public int getMag(){
		return mag;
	}

	public int getDef(){
		return def;
	}

	public int getRes(){
		return res;
	}

	public double getAar(){
		return aar;
	}

	public double getSar(){
		return sar;
	}

	public double getAkb(){
		return akb;
	}

	public double getSkb(){
		return skb;
	}

	public double getAhit(){
		return ahit;
	}

	public double getShit(){
		return shit;
	}

	//取得實際攻擊和防禦

	public int getRealAtk(){
		int equipmentAtkIncrease = 0; //裝備物理攻擊提升
		double equipmentAtkTimes = 1; //裝備物理攻擊加倍
		return (int) (getAtk() * equipmentAtkTimes) + equipmentAtkIncrease;
	}

	public int getRealMag(){
		int equipmentMagIncrease = 0;
		double equipmentMagTimes = 1;
		return (int) (getMag() * equipmentMagTimes) + equipmentMagIncrease;
	}

	public int getRealDef(){
		int equipmentDefIncrease = 0;
		double equipmentDefTimes = 1;
		return (int) (getDef() * equipmentDefTimes) + equipmentDefIncrease;
	}

	public int getRealRes(){
		int equipmentResIncrease = 0;
		double equipmentResTimes = 1;
		return (int) (getRes() * equipmentResTimes) + equipmentResIncrease;
	}

	public double getRealAar(){
		double equipmentAarIncrease = 0;
		double equipmentAarTimes = 1;
		return getAar() * equipmentAarTimes + equipmentAarIncrease;
	}

	public double getRealSar(){
		double equipmentSarIncrease = 0;
		double equipmentSarTimes = 1;
		return getSar() * equipmentSarTimes + equipmentSarIncrease;
	}

	public double getRealAkb(){
		double equipmentAkbIncrease = 0;
		double equipmentAkbTimes = 1;
		return getAkb() * equipmentAkbTimes + equipmentAkbIncrease;
	}

	public double getRealSkb(){
		double equipmentSkbIncrease = 0;
		double equipmentSkbTimes = 1;
		return getSkb() * equipmentSkbTimes + equipmentSkbIncrease;
	}

	public double getRealAhit(){
		double equipmentAhitIncrease = 0;
		double equipmentAhitTimes = 1;
		return getAhit() * equipmentAhitTimes + equipmentAhitIncrease;
	}

	public double getRealShit(){
		double equipmentShitIncrease = 0;
		double equipmentShitTimes = 1;
		return getShit() * equipmentShitTimes + equipmentShitIncrease;
	}

	//攻擊與受傷

	//物理攻擊
	public void attack(Entity entity){

	}
}
