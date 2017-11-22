package insomnia.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CPUTime
{

	public static long getCpuTimeNano()
	{
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported()
				? bean.getCurrentThreadCpuTime()
				: 0L;
	}

	public static long getUserTimeNano()
	{
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported()
				? bean.getCurrentThreadUserTime()
				: 0L;
	}

	public static long getSystemTimeNano()
	{
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported()
				? (bean.getCurrentThreadCpuTime()
						- bean.getCurrentThreadUserTime())
				: 0L;
	}
}